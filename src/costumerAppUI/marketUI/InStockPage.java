package costumerAppUI.marketUI;

import costumerAppUI.BuyWindow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;
import model.Good;
import model.Market;
import costumerAppUI.sample.Main;
import costumerAppUI.sample.OutputProcessor;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class InStockPage implements Initializable {
    public TableView<Good> inStockTable;
    public TableColumn<Good, Integer> iD;
    public TableColumn<Good, String> name;
    public TableColumn<Good, Integer> inventory;
    public TableColumn<Good, Long> price;
    public static ObservableList<Good> inStockItems;

    public void returnButton(){
        Main.window.setScene(Main.costumerMenuScene);
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

    public static void setInStockItems(){
        inStockItems = FXCollections.observableArrayList();
        for (Good good : Market.getStorage()) {
            if(good.isAvailable()) {
                inStockItems.add(good);
            }
        }

    }

    public void setColumns(){
        name.setCellValueFactory(new PropertyValueFactory<Good, String>("name"));

        iD.setCellValueFactory(new PropertyValueFactory<Good, Integer>("id"));

        inventory.setCellValueFactory(new PropertyValueFactory<Good, Integer>("vol"));

        price.setCellValueFactory(new PropertyValueFactory<Good, Long>("sellPrice"));
    }

    public void showBalanceButton(){
        OutputProcessor.showBalance();
    }

    public void buyButton() throws IOException {
        Good selectedGood = inStockTable.getSelectionModel().getSelectedItem();
        if(selectedGood == null){
            OutputProcessor.noSelection();
            return;
        }
        setupBuyBoxScene();
        BuyWindow.display(selectedGood, inStockTable);
    }

    public void setupBuyBoxScene() throws IOException {
        Parent buyBox = FXMLLoader.load(getClass().getResource("/costumerAppUI/buyWindow.fxml"));
        Main.buyBoxScene = new Scene(buyBox, 300, 150);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setInStockItems();
        setColumns();
        inStockTable.setItems(inStockItems);
        inStockTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }
}
