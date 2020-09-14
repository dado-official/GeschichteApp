package sample.GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class Workinprogress {

    @FXML
    private Button zurück;


    @FXML
    void handleButtonAction(ActionEvent event) throws IOException {
        if(event.getSource()==zurück){
            Stage stage = (Stage) zurück.getScene().getWindow();
            FXMLLoader fxmlloader = new FXMLLoader();
            Pane root = fxmlloader.load(getClass().getResource("hauptmenu.fxml").openStream());
            Scene scene = new Scene(root, 400, 600);
            stage.setScene(scene);
            scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        }
    }
}
