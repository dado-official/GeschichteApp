package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;


public class Controller2 {
    @FXML
    private Button zuruck;
    @FXML
    private Button aktuellthema;
    @FXML
    private Button letztefehler;

    @FXML
    private MenuButton themen;

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
        } else if (event.getSource() == aktuellthema) {
            Stage stage1 = (Stage) aktuellthema.getScene().getWindow();

            Stage stage2 = new Stage();
            FXMLLoader fxmlloader = new FXMLLoader();
            stage2.initStyle(StageStyle.UNDECORATED);
            Pane root = fxmlloader.load(getClass().getResource("quizlayout.fxml").openStream());
            Scene scene = new Scene(root, 400, 600);
            stage2.setScene(scene);
            scene.getStylesheets().add(getClass().getResource("quizstyle.css").toExternalForm());
            stage2.setResizable(false);
            Quizlayout controller = fxmlloader.<Quizlayout>getController();
            controller.randomizeQuestion("Julikrise");
            stage2.show();
            stage1.close();


        } else if (event.getSource() == letztefehler) {
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
            Quizlayout controller = fxmlloader.<Quizlayout>getController();
            controller.randomizeQuestion("wrongAnswers");
        }


    }

    public void handleItemAction(ActionEvent actionEvent) throws IOException {
        MenuItem clickedMenuItem = (MenuItem) actionEvent.getTarget();
        String buttonlabel = clickedMenuItem.getText();
        //Die Themenbereiche werden in topicList gespeichert
        File f = new File("Themenbereiche");
        String[] topicList = f.list();

        String chosenTopic = buttonlabel;
        FileHandler fileHandler = new FileHandler(chosenTopic + ".txt");

        Stage stage1 = (Stage) aktuellthema.getScene().getWindow();
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
        Quizlayout controller = fxmlloader.<Quizlayout>getController();
        controller.randomizeQuestion(chosenTopic);
    }
    public void addthema(String topic){
        themen.getItems().add(new MenuItem(topic));
        System.out.println("aufgerufen");
    }

}

