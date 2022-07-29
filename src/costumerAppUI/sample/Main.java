package costumerAppUI.sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static Stage window;
    public static Scene loginScene;
    public static Scene registrationScene;
    public static Scene exitBoxScene;
    public static Scene costumerMenuScene;
    public static Scene inStockMenuScene;
    public static Scene outOfStockMenuScene;
    public static Scene buyBoxScene;
    public static Scene orderHistoryScene;

    @Override
    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;
        setupLoginScene();

        window.setTitle("Sharif Market");
        window.setScene(loginScene);
        window.show();
    }

    public void setupLoginScene() throws IOException {
        Parent loginPage = FXMLLoader.load(getClass().getResource("loginMenu.fxml"));
        Main.loginScene = new Scene(loginPage, 600, 400);
        Main.window.setScene(Main.loginScene);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
