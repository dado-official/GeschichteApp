package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Quit {
    @FXML
    private Button ja;

    @FXML
    private Button nein;


    @FXML
    void handleButtonAction(ActionEvent event) throws IOException {
        if (event.getSource() == ja){
            Platform.exit();
        } else if (event.getSource() == nein){
            Stage stage1 = (Stage) nein.getScene().getWindow();

            Stage stage2 = new Stage();
            FXMLLoader fxmlloader = new FXMLLoader();
            stage2.initStyle(StageStyle.UNDECORATED);
            Pane root = fxmlloader.load(getClass().getResource("geschichtequiz.fxml").openStream());
            Scene scene = new Scene(root, 400, 600);
            stage2.setScene(scene);
            scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
            stage2.setResizable(false);
            stage2.show();
            stage1.close();
        }
    }
}
