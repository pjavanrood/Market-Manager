package adminAppUI;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EntrancePage{

    public void adminEnterClicked() throws IOException {
        setupAdminScene();
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

    public void setupAdminScene() throws IOException {
        Parent entrancePage = FXMLLoader.load(getClass().getResource("AdminMenu.fxml"));
        Main.adminMenuScene = new Scene(entrancePage, 600, 400);
        Main.window.setScene(Main.adminMenuScene);
    }

}
