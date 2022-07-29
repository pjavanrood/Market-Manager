package adminAppUI;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class AdminMenu {

    public void returnButtonClicked(){
        Main.window.setScene(Main.entrancePageScene);
    }

    public void productsButtonClicked() throws IOException {setGoodsPageScene();}

    public void ordersButtonClicked() throws IOException {setOrdersPageScene();}

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

    public void setGoodsPageScene() throws IOException {
        Parent goodsTablePage = FXMLLoader.load(getClass().getResource("goodsTables.fxml"));
        Main.goodsPageScene = new Scene(goodsTablePage, 1200, 800);
        Main.window.setScene(Main.goodsPageScene);
    }

    public void setOrdersPageScene() throws IOException {
        Parent goodsTablePage = FXMLLoader.load(getClass().getResource("ordersTables.fxml"));
        Main.ordersPageScene = new Scene(goodsTablePage, 1200, 800);
        Main.window.setScene(Main.ordersPageScene);
    }


}
