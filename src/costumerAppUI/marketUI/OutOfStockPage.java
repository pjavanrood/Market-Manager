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
import model.Good;
import model.Market;
import costumerAppUI.sample.Main;

import java.net.URL;
import java.util.ResourceBundle;

public class OutOfStockPage implements Initializable {

    public TableView<Good> outOfStockTable;
    public static ObservableList<Good> outOfStockItems;
    public TableColumn<Good, Integer> iD;
    public TableColumn<Good, String> name;
    public TableColumn<Good, Long> price;

    public static void setInStockItems(){
        outOfStockItems = FXCollections.observableArrayList();
        for (Good good : Market.getStorage()) {
            if(!good.isAvailable()) {
                outOfStockItems.add(good);
            }
        }

    }

    public void setColumns(){
        name.setCellValueFactory(new PropertyValueFactory<Good, String>("name"));

        iD.setCellValueFactory(new PropertyValueFactory<Good, Integer>("id"));

        price.setCellValueFactory(new PropertyValueFactory<Good, Long>("sellPrice"));
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

    public void returnButton(){
        Main.window.setScene(Main.costumerMenuScene);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setInStockItems();
        setColumns();
        outOfStockTable.setItems(outOfStockItems);
        outOfStockTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }
}
