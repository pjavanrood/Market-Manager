package adminAppUI;

import com.sun.javafx.binding.IntegerConstant;
import costumerAppUI.sample.OutputProcessor;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;
import javafx.util.converter.FormatStringConverter;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.LongStringConverter;
import model.Good;
import model.Market;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GoodsTables implements Initializable {
    ObservableList<Good> goodObservableList;

    ObservableList<Good> availableGoods;

    ObservableList<Good> unavailableGoods;

    @FXML
    private TableView<Good> goodsTable;

    @FXML
    private TableColumn<Good, Integer> idColumn;

    @FXML
    private TableColumn<Good, String> nameColumn;

    @FXML
    private TableColumn<Good, Integer> inventoryColumn;

    @FXML
    private TableColumn<Good, Long> sellPriceColumn;

    @FXML
    private TableColumn<Good, Long> buyPriceColumn;

    @FXML
    private TableColumn<Good, Boolean> availabilityColumn;

    @FXML
    private TableColumn<Good, Long> sellVolColumn;

    @FXML
    private TableColumn<Good, Long> totalSellColumn;

    @FXML
    private TableColumn<Good, Long> profitColumn;

    @FXML
    private TextField nameField;

    @FXML
    private TextField buyPriceField;

    @FXML
    private TextField volField;

    @FXML
    private TextField sellPriceField;

    @FXML
    private Button addButton;

    @FXML
    private Button clearButton;

    @FXML
    private Button deleteButton;

    @FXML
    private ChoiceBox<String> modeChoiceBox;

    @FXML
    private ChoiceBox<String> availabilityChoiceBox;

    public void setColumns(){
        goodsTable.setEditable(true);

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        nameColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Good, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Good, String> goodStringCellEditEvent) {
                goodsTable.getSelectionModel().getSelectedItem().changeNameRequest(goodStringCellEditEvent.getNewValue());
                goodsTable.refresh();
            }
        });

        inventoryColumn.setCellValueFactory(new PropertyValueFactory<>("vol"));
        inventoryColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        inventoryColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Good, Integer>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Good, Integer> goodIntegerCellEditEvent) {
                goodsTable.getSelectionModel().getSelectedItem().changeVolumeRequest(goodIntegerCellEditEvent.getNewValue());
                goodsTable.refresh();
            }
        });


        sellPriceColumn.setCellValueFactory(new PropertyValueFactory<>("sellPrice"));
        sellPriceColumn.setCellFactory(TextFieldTableCell.forTableColumn(new LongStringConverter()));
        sellPriceColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Good, Long>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Good, Long> goodLongCellEditEvent) {
                goodsTable.getSelectionModel().getSelectedItem().changeSellPriceRequest(goodLongCellEditEvent.getNewValue());
                goodsTable.refresh();
            }

        });


        buyPriceColumn.setCellValueFactory(new PropertyValueFactory<>("buyPrice"));
        buyPriceColumn.setCellFactory(TextFieldTableCell.forTableColumn(new LongStringConverter()));
        buyPriceColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Good, Long>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Good, Long> goodLongCellEditEvent) {
                goodsTable.getSelectionModel().getSelectedItem().changeBuyPriceRequest(goodLongCellEditEvent.getNewValue());
                goodsTable.refresh();
            }

        });

        availabilityColumn.setCellValueFactory(new PropertyValueFactory<>("available"));
        sellVolColumn.setCellValueFactory(new PropertyValueFactory<>("sellVol"));
        totalSellColumn.setCellValueFactory(new PropertyValueFactory<>("totalSellPrice"));
        profitColumn.setCellValueFactory(new PropertyValueFactory<>("profit"));
    }

    public void setGoodObservableList() {
        goodObservableList = FXCollections.observableArrayList();
        availableGoods = FXCollections.observableArrayList();
        unavailableGoods = FXCollections.observableArrayList();

        goodObservableList.addAll(Market.getStorage());
        for (Good good : goodObservableList) {
            if(good.isAvailable())
                availableGoods.add(good);
            else
                unavailableGoods.add(good);
        }
    }

    public void updateLists(){
        availableGoods = FXCollections.observableArrayList();
        unavailableGoods = FXCollections.observableArrayList();
        for (Good good : goodObservableList) {
            if(good.isAvailable())
                availableGoods.add(good);
            else
                unavailableGoods.add(good);
        }
    }

    public void addButtonClicked(){
        int result = Good.checkCorrectInputs(nameField.getText(), buyPriceField.getText(), sellPriceField.getText(), volField.getText());
        switch (result) {
            case -1 -> OutputProcessor.existingGood();
            case -2 -> OutputProcessor.invalidInput();
            case 0 -> {
                goodObservableList.add(Good.addGood(nameField.getText(), buyPriceField.getText(), sellPriceField.getText(), volField.getText()));
                updateLists();
                goodsTable.refresh();
                OutputProcessor.successfulAdd();
                clearButtonClicked();
            }
        }
    }

    public void clearButtonClicked(){
        nameField.setText("");
        buyPriceField.setText("");
        volField.setText("");
        sellPriceField.setText("");
    }

    public void deleteButtonClicked(){
        Good selectedGood = goodsTable.getSelectionModel().getSelectedItem();
        goodObservableList.remove(selectedGood);
        if(selectedGood.isAvailable())
            availableGoods.remove(selectedGood);
        else
            unavailableGoods.remove(selectedGood);
        Market.removeGood(selectedGood);
        updateLists();
        goodsTable.refresh();
    }

    public void initializeChoiceBox(){
        ObservableList<String> modeChoiceBoxItems = FXCollections.observableArrayList();
        modeChoiceBoxItems.addAll("Statistical", "Simple");
        modeChoiceBox.setItems(modeChoiceBoxItems);
        modeChoiceBox.setValue("Statistical");
        modeChoiceBox.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> modeSelected());

        ObservableList<String> availabilityChoiceBoxItems = FXCollections.observableArrayList();
        availabilityChoiceBoxItems.addAll("Available", "Unavailable", "All");
        availabilityChoiceBox.setItems(availabilityChoiceBoxItems);
        availabilityChoiceBox.setValue("All");
        availabilityChoiceBox.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> availabilitySelected());
    }

    public void availabilitySelected(){
        String availability = availabilityChoiceBox.getSelectionModel().getSelectedItem();
        if(availability.equals("Available")){
            goodsTable.setItems(availableGoods);
        }

        else if(availability.equals("Unavailable")){
            goodsTable.setItems(unavailableGoods);
        }

        else{
            goodsTable.setItems(goodObservableList);
        }
        goodsTable.refresh();

    }

    public void modeSelected(){
        String mode = modeChoiceBox.getSelectionModel().getSelectedItem();
        if(mode.equals("Simple")){
            sellVolColumn.setVisible(false);
            totalSellColumn.setVisible(false);
            profitColumn.setVisible(false);
            return;
        }

        sellVolColumn.setVisible(true);
        totalSellColumn.setVisible(true);
        profitColumn.setVisible(true);
    }

    public void glowButton(javafx.scene.input.MouseEvent mouseEvent) {
        Glow glow = new Glow();
        glow.setLevel(0.5);
        ((ImageView) mouseEvent.getSource()).setEffect(glow);

    }

    public void unGlowButton(javafx.scene.input.MouseEvent mouseEvent) {
        Glow glow = new Glow();
        glow.setLevel(0);
        ((ImageView) mouseEvent.getSource()).setEffect(glow);
    }

    public void returnButtonClicked() throws IOException {
        setupAdminScene();
    }

    public void setupAdminScene() throws IOException {
        Parent entrancePage = FXMLLoader.load(getClass().getResource("AdminMenu.fxml"));
        Main.adminMenuScene = new Scene(entrancePage, 600, 400);
        Main.window.setScene(Main.adminMenuScene);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeChoiceBox();
        setGoodObservableList();
        setColumns();
        goodsTable.setItems(goodObservableList);
        goodsTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }
}
