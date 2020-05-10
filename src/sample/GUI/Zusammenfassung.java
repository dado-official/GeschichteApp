package sample.GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Zusammenfassung {
    @FXML
    private Button zurück;

    @FXML
    private Label right;

    @FXML
    private Label wrong;


    @FXML
    void handleButtonAction(ActionEvent event) throws IOException {
        Stage stage1 = (Stage) zurück.getScene().getWindow();

        Stage stage2 = new Stage();
        FXMLLoader fxmlloader = new FXMLLoader();
        stage2.initStyle(StageStyle.UNDECORATED);
        Pane root = fxmlloader.load(getClass().getResource("neuesQuiz.fxml").openStream());
        Scene scene = new Scene(root, 400, 600);
        stage2.setScene(scene);
        scene.getStylesheets().add(getClass().getResource("style2.css").toExternalForm());
        stage2.setResizable(false);
        stage2.show();
        stage1.close();
    }

    public void showLabelContext(int richtig, int falsch){
        right.setText(String.valueOf(richtig));
        wrong.setText(String.valueOf(falsch));
    }
}
