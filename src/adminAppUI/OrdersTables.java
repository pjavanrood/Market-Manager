package adminAppUI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import model.*;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class OrdersTables implements Initializable {
    ObservableList<Order> ordersObservableList;

    @FXML
    private TableView<Order> ordersTable;

    @FXML
    private TableColumn<Order, String> idColumn;

    @FXML
    private TableColumn<Order, String> costumerIdColumn;

    @FXML
    private TableColumn<Order, Integer> goodIdColumn;

    @FXML
    private TableColumn<Order, Integer> volColumn;

    @FXML
    private TableColumn<Order, Long> priceColumn;

    @FXML
    private TableColumn<Order, LocalDateTime> dateTimeColumn;



    public void glowButton(MouseEvent mouseEvent) {
        Glow glow = new Glow();
        glow.setLevel(0.5);
        ((ImageView) mouseEvent.getSource()).setEffect(glow);

    }

    public void unGlowButton(MouseEvent mouseEvent) {
        Glow glow = new Glow();
        glow.setLevel(0);
        ((ImageView) mouseEvent.getSource()).setEffect(glow);
    }

    public void returnButtonClicked() throws IOException {
        setupAdminScene();
    }

    public void setOrdersObservableList(){
        ordersObservableList = FXCollections.observableArrayList();
        ordersObservableList.addAll(Market.orders);
    }

    public void setColumns(){
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        costumerIdColumn.setCellValueFactory(new PropertyValueFactory<>("costumerId"));
        goodIdColumn.setCellValueFactory(new PropertyValueFactory<>("orderedGoodId"));
        volColumn.setCellValueFactory(new PropertyValueFactory<>("orderVol"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        dateTimeColumn.setCellValueFactory(new PropertyValueFactory<>("orderTime"));
    }

    public void setupAdminScene() throws IOException {
        Parent entrancePage = FXMLLoader.load(getClass().getResource("AdminMenu.fxml"));
        Main.adminMenuScene = new Scene(entrancePage, 600, 400);
        Main.window.setScene(Main.adminMenuScene);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setOrdersObservableList();
        setColumns();
        ordersTable.setItems(ordersObservableList);
    }
}
