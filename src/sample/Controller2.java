package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;




public class Controller2 {
    @FXML
    private Button zuruck;
    @FXML
    private Button neuesthema;
    @FXML
    private Button letztefehler;
    @FXML
    private Button allethemen;

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        if (event.getSource() == zuruck) {
            Stage stage1 = (Stage) zuruck.getScene().getWindow();

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
        } else if(event.getSource() == neuesthema){
            Stage stage1 = (Stage) neuesthema.getScene().getWindow();

            Stage stage2 = new Stage();
            FXMLLoader fxmlloader = new FXMLLoader();
            stage2.initStyle(StageStyle.UNDECORATED);
            Pane root = fxmlloader.load(getClass().getResource("quizlayout.fxml").openStream());
            Scene scene = new Scene(root, 400, 600);
            stage2.setScene(scene);
            scene.getStylesheets().add(getClass().getResource("quizstyle.css").toExternalForm());
            stage2.setResizable(false);
            stage2.show();
            stage1.close();
        } else if(event.getSource() == letztefehler){
            Stage stage1 = (Stage) letztefehler.getScene().getWindow();

            Stage stage2 = new Stage();
            FXMLLoader fxmlloader = new FXMLLoader();
            stage2.initStyle(StageStyle.UNDECORATED);
            Pane root = fxmlloader.load(getClass().getResource("quizlayout.fxml").openStream());
            Scene scene = new Scene(root, 400, 600);
            stage2.setScene(scene);
            scene.getStylesheets().add(getClass().getResource("quizstyle.css").toExternalForm());
            stage2.setResizable(false);
            stage2.show();
            stage1.close();
        } else if(event.getSource() == allethemen){
            Stage stage1 = (Stage) allethemen.getScene().getWindow();

            Stage stage2 = new Stage();
            FXMLLoader fxmlloader = new FXMLLoader();
            stage2.initStyle(StageStyle.UNDECORATED);
            Pane root = fxmlloader.load(getClass().getResource("quizlayout.fxml").openStream());
            Scene scene = new Scene(root, 400, 600);
            stage2.setScene(scene);
            scene.getStylesheets().add(getClass().getResource("quizstyle.css").toExternalForm());
            stage2.setResizable(false);
            stage2.show();
            stage1.close();
        }
    }
}
