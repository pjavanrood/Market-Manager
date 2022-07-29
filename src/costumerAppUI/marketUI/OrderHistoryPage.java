package costumerAppUI.marketUI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;
import model.Costumer;
import model.Market;
import model.Order;
import costumerAppUI.sample.Main;
import costumerAppUI.sample.OutputProcessor;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class OrderHistoryPage implements Initializable {

    public TableView<Order> ordersTable;
    public static ObservableList<Order> ordersItems;
    public TableColumn<Order, String> orderId;
    public TableColumn<Order, String> costumerId;
    public TableColumn<Order, String> orderGood;
    public TableColumn<Order, Integer> quantity;
    public TableColumn<Order, LocalDateTime> dateTime;
    public TableColumn<Order, Long> totalPrice;


    public void cancelOrderClicked(){
        Order selectedOrder = ordersTable.getSelectionModel().getSelectedItem();
        if(selectedOrder == null){
            OutputProcessor.noSelection();
            return;
        }
        selectedOrder.cancelOrder(Costumer.getCostumer());
        updateTable();
    }

    public static void setOrdersItems(){
        ordersItems = FXCollections.observableArrayList();
        if(Costumer.getCostumer() == null)
            return;
        String costumerId = Costumer.getCostumer().getId();
        for (Order order : Market.orders) {
            if(order.getCostumerId().equals(costumerId)){
                ordersItems.add(order);
            }
        }
    }

    public void setColumns(){
        orderId.setCellValueFactory(new PropertyValueFactory<Order, String>("id"));

        costumerId.setCellValueFactory(new PropertyValueFactory<Order, String>("costumerId"));

        orderGood.setCellValueFactory(new PropertyValueFactory<Order, String>("orderedGoodId"));

        quantity.setCellValueFactory(new PropertyValueFactory<Order, Integer>("orderVol"));

        dateTime.setCellValueFactory(new PropertyValueFactory<Order, LocalDateTime>("orderTime"));

        totalPrice.setCellValueFactory(new PropertyValueFactory<Order, Long>("totalPrice"));
    }

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setOrdersItems();
        setColumns();
        ordersTable.setItems(ordersItems);
        ordersTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    public void updateTable(){
        for (int i = ordersTable.getItems().size() - 1; i > -1; i--) {
            if(ordersTable.getItems().get(i).isOrderCancel())
                ordersTable.getItems().remove(i);
        }
        ordersTable.refresh();
    }


}
