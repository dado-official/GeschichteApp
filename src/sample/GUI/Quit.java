package sample.GUI;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class Quit {
    @FXML
    private Button ja;

    @FXML
    private Button nein;
    private double xOffset = 0;
    private double yOffset = 0;

    @FXML
    void handleButtonAction(ActionEvent event) throws IOException {
        if (event.getSource() == ja){
            Platform.exit();
        } else if (event.getSource() == nein) {
            Stage stage = (Stage) nein.getScene().getWindow();

            FXMLLoader fxmlloader = new FXMLLoader();
            Pane root = fxmlloader.load(getClass().getResource("hauptmenu.fxml").openStream());
            Scene scene = new Scene(root, 400, 600);
            root.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    xOffset = event.getSceneX();
                    yOffset = event.getSceneY();
                }
            });
            root.setOnMouseDragged(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    stage.setX(event.getScreenX() - xOffset);
                    stage.setY(event.getScreenY() - yOffset);
                }
            });
            stage.setScene(scene);
            scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        }
    }
}
