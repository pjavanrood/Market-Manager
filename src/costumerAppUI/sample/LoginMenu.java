package costumerAppUI.sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Costumer;

import java.io.IOException;

public class LoginMenu {
    public static Stage window;
    public TextField username;
    public PasswordField password;
    public Hyperlink createAccount;
    public PasswordField passwordField;
    public PasswordField reTypePassword;
    public TextField name;
    public TextField id;
    public Button registrationButton;

    public void handleLoginInputs() throws IOException {
        String enteredUsername = username.getText();
        String enteredPassword =password.getText();

        int result = HandleCostumerLogin.loginRequest(enteredUsername, enteredPassword);

        if(result == -1){
            OutputProcessor.unregisteredUsername();
            return;
        }

        if(result == -2){
            OutputProcessor.wrongPassword();
            return;
        }

        Costumer.setCostumer(Costumer.findCostumer(enteredUsername));
        setupCostumerMenuScene();
    }

    public void handleRegistrationInputs(){
        String enteredName = name.getText();
        String enteredId = id.getText();
        String enteredPassword = passwordField.getText();
        String reTypePass = reTypePassword.getText();

        int result = HandleCostumerLogin.registerRequest(enteredName, enteredId, enteredPassword, reTypePass);

        if(result == -1){
            OutputProcessor.existingUser();
            return;
        }

        if(result == -2){
            OutputProcessor.notMatchingPasswords();
            return;
        }

        window.close();
        OutputProcessor.successfulRegister();
        System.out.println("Register Successful");
    }

    public void displayRegistration() throws IOException {
        window = new Stage();
        setupRegistrationScene();
        setupExitScene();
        window.initModality(Modality.APPLICATION_MODAL);
        Scene scene = Main.registrationScene;

        window.setOnCloseRequest(e -> {
            e.consume();
            closeRegistration();
        });

        window.setTitle("Registration Form");
        window.setScene(scene);
        window.showAndWait();
    }

    public void closeRegistration(){
        ExitBox.display();
    }

    public void setupRegistrationScene() throws IOException {
        Parent registerPage = FXMLLoader.load(getClass().getResource("registerForm.fxml"));
        Main.registrationScene = new Scene(registerPage, 600, 400);
    }

    public void setupExitScene() throws IOException {
        Parent exitBox = FXMLLoader.load(getClass().getResource("exitBox.fxml"));
        Main.exitBoxScene = new Scene(exitBox, 377, 146);
    }

    public void setupCostumerMenuScene() throws IOException {
        Parent costumerMenu = FXMLLoader.load(getClass().getResource("/costumerAppUI/costumerMenu.fxml"));
        Main.costumerMenuScene = new Scene(costumerMenu, 600, 400);
        Main.window.setScene(Main.costumerMenuScene);
    }
}
