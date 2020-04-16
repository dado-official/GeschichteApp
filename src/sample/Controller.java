package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;

public class Controller {
    @FXML
    private Button alt;

    @FXML
    private Button close;

    @FXML
    private Button neu;


    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        if (event.getSource() == close) {
            Stage stage1 = (Stage) neu.getScene().getWindow();

            Stage stage2 = new Stage();
            FXMLLoader fxmlloader = new FXMLLoader();
            stage2.setTitle("Neues Quiz");
            Pane root = fxmlloader.load(getClass().getResource("quit.fxml").openStream());
            Scene scene = new Scene(root, 400, 600);
            stage2.setScene(scene);
            scene.getStylesheets().add(getClass().getResource("stylequit.css").toExternalForm());
            stage2.setResizable(false);
            stage2.show();
            stage1.close();
        } else if (event.getSource() == neu) {
            Stage stage1 = (Stage) neu.getScene().getWindow();

            Stage stage2 = new Stage();
            FXMLLoader fxmlloader = new FXMLLoader();
            stage2.setTitle("Neues Quiz");
            Pane root = fxmlloader.load(getClass().getResource("geschichtequiz2.fxml").openStream());
            Scene scene = new Scene(root, 400, 600);
            stage2.setScene(scene);
            scene.getStylesheets().add(getClass().getResource("style2.css").toExternalForm());
            stage2.setResizable(false);
            stage2.show();
            stage1.close();

        } else if(event.getSource() == alt){
            Stage stage1 = (Stage) neu.getScene().getWindow();

            Stage stage2 = new Stage();
            FXMLLoader fxmlloader = new FXMLLoader();
            stage2.setTitle("Alte Quiz");
            Pane root = fxmlloader.load(getClass().getResource("geschichtequiz3.fxml").openStream());
            Scene scene = new Scene(root, 400, 600);
            stage2.setScene(scene);
            scene.getStylesheets().add(getClass().getResource("style3.css").toExternalForm());
            stage2.setResizable(false);
            stage2.show();
            stage1.hide();
        }
    }


    public void menuClickHandler(ActionEvent actionEvent) throws IOException {
        MenuItem clickedMenu = (MenuItem) actionEvent.getTarget();
        String menuLabel = clickedMenu.getText();

        if("Infos".equals(menuLabel)){
            JOptionPane.showMessageDialog(null,"Programmiert von Daniel Nagler, Silas Keim, Sebastian Hofer\n" + "Im Auftrag von Meinhard Mair","Infos", JOptionPane.PLAIN_MESSAGE);
        }
    }
}
