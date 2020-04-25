package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Quizalt {

    @FXML
    private Button weiter;
    @FXML
    private Button zuruck;

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        if (event.getSource() == zuruck) {
            Stage stage1 = (Stage) zuruck.getScene().getWindow();

            Stage stage2 = new Stage();
            FXMLLoader fxmlloader = new FXMLLoader();
            stage2.initStyle(StageStyle.UNDECORATED);
            Pane root = fxmlloader.load(getClass().getResource("geschichtequiz2.fxml").openStream());
            Scene scene = new Scene(root, 400, 600);
            stage2.setScene(scene);
            scene.getStylesheets().add(getClass().getResource("style2.css").toExternalForm());
            stage2.setResizable(false);
            stage2.show();
            stage1.close();
        }
    }

}
