package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Controller3 {
    @FXML
    private Button zuruck;
    @FXML
    private MenuButton menu;

    @FXML
    void handleButtonAction(ActionEvent event) throws IOException {
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

        }
    }

    public void handleItemAction(ActionEvent actionEvent) throws IOException {
        MenuItem clickedMenuItem = (MenuItem) actionEvent.getTarget();
        String buttonlabel = clickedMenuItem.getText();
        System.out.println(buttonlabel);
        Stage stage1 = (Stage) menu.getScene().getWindow();

        Stage stage2 = new Stage();
        FXMLLoader fxmlloader = new FXMLLoader();
        stage2.initStyle(StageStyle.UNDECORATED);
        Pane root = fxmlloader.load(getClass().getResource("quizalt.fxml").openStream());
        Scene scene = new Scene(root, 400, 600);
        stage2.setScene(scene);
        scene.getStylesheets().add(getClass().getResource("quizalt.css").toExternalForm());
        stage2.setResizable(false);
        Quizalt controller =fxmlloader.getController();
        controller.showQuestion(buttonlabel);
        stage2.show();
        stage1.close();
    }

    public void addMenuItem(String name){
        MenuItem m = new MenuItem(name);
        menu.getItems().addAll(m);
        m.setOnAction((actionEvent -> {
            try {
                handleItemAction(actionEvent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));


        System.out.println("Aufgerufen");

    }
}
