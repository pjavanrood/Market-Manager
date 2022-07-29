package costumerAppUI;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.Glow;
import costumerAppUI.sample.Main;
import costumerAppUI.sample.OutputProcessor;
import javafx.scene.image.ImageView;
import java.io.IOException;


public class CostumerMenu {
    @FXML
    ImageView balanceButton;

    public void showBalanceButton(){
        OutputProcessor.showBalance();
    }

    public void logoutButton(){
        Main.window.setScene(Main.loginScene);
    }

    public void inStockButton() throws IOException {
        setupInStockScene();
    }

    public void outOfStockButton() throws IOException {
        setupOutOfStockScene();
    }

    public void orderHistoryButton() throws IOException {
        setupOrderHistoryScene();
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

    public void setupInStockScene() throws IOException {
        Parent inStockMenu = FXMLLoader.load(getClass().getResource("/costumerAppUI/marketUI/inStockPage.fxml"));
        Main.inStockMenuScene = new Scene(inStockMenu, 600, 400);
        Main.window.setScene(Main.inStockMenuScene);
    }

    public void setupOutOfStockScene() throws IOException {
        Parent outOfStockMenu = FXMLLoader.load(getClass().getResource("/costumerAppUI/marketUI/outOfStockPage.fxml"));
        Main.outOfStockMenuScene = new Scene(outOfStockMenu, 600, 400);
        Main.window.setScene(Main.outOfStockMenuScene);
    }

    public void setupOrderHistoryScene() throws IOException {
        Parent orderHistory = FXMLLoader.load(getClass().getResource("/costumerAppUI/marketUI/orderHistoryPage.fxml"));
        Main.orderHistoryScene = new Scene(orderHistory, 600, 400);
        Main.window.setScene(Main.orderHistoryScene);
    }
}
