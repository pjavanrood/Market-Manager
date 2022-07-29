package costumerAppUI.sample;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ExitBox {
    public static Stage window;
    public Button yesButton;
    public Button noButton;
    public static boolean exit;

    public static void display(){
        exit = false;

        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        Scene scene = Main.exitBoxScene;

        window.setTitle("Registration Form");
        window.setScene(scene);
        window.show();
        }

    public void yesButton(){
        exit = true;
        window.close();
        LoginMenu.window.close();
    }

    public void noButton(){
        exit = false;
        window.close();
    }

}
