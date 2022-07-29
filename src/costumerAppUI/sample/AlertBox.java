package costumerAppUI.sample;


import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox{
    public static Label alertLabelStatic;
    public static Stage window;

    public void display(String label){
        alertLabelStatic = new Label(label);
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);

        VBox vBox = new VBox(10);
        Button closeButton = new Button("Close!");
        closeButton.setOnAction(e -> window.close());

        vBox.getChildren().addAll(alertLabelStatic, closeButton);
        vBox.setAlignment(Pos.CENTER);
        Scene scene = new Scene(vBox, 150, 100);
        window.setTitle("Alert");
        window.setScene(scene);
        window.show();
    }
}
