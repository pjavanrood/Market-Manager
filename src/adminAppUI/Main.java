package adminAppUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    static Stage window;
    static Scene entrancePageScene;
    static Scene goodsPageScene;
    static Scene ordersPageScene;
    static Scene adminMenuScene;

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        setUpEntranceScene();

        window.setTitle("Admin Panel");
        window.setScene(entrancePageScene);
        window.show();
    }

    public void setUpEntranceScene() throws IOException {
        Parent entrancePage = FXMLLoader.load(getClass().getResource("entrancePage.fxml"));
        entrancePageScene = new Scene(entrancePage, 600, 400);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
