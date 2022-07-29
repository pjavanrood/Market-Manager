package costumerAppUI;

import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Costumer;
import model.Good;
import costumerAppUI.sample.Main;
import costumerAppUI.sample.OutputProcessor;

public class BuyWindow {
    public TextField textField;
    public static Good desiredGood;
    public static TableView<Good> table;
    static Stage window;
    static String quantity;

    public static void display(Good good, TableView<Good> tableView){
        desiredGood = good;
        table = tableView;
        quantity = "";
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        Scene scene = Main.buyBoxScene;

        window.setTitle("Buy");
        window.setScene(scene);
        window.show();
    }

    public void buyButtonClicked(){
        quantity = textField.getText();
        int result = Costumer.checkQuantity(desiredGood, quantity);
        switch (result) {
            case -1 -> OutputProcessor.invalidInput();
            case -2 -> OutputProcessor.notEnoughGood();
            case -3 -> OutputProcessor.notEnoughBalance();
            case 0 -> {
                Costumer.buyGood(Costumer.getCostumer(), desiredGood, Integer.parseInt(quantity));
                updateTable();
                window.close();
            }
        }
    }

    public void updateTable(){
        for (int i = table.getItems().size() - 1; i > -1; i--) {
            if(!table.getItems().get(i).isAvailable())
                table.getItems().remove(i);
        }
        table.refresh();
    }

}
