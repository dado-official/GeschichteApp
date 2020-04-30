package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Quizalt {

    @FXML
    private Button weiter;
    @FXML
    private Button zuruck;

    @FXML
    private TextField answer1;

    @FXML
    private TextField answer2;

    @FXML
    private TextField answer3;

    @FXML
    private Label frage;
    private int rand_int1,counter;
    int counter2=0;
    String topic;

    //ArrayList zum Speichern der bereits angezeigten Fragen

    private FileHandlerAlt filehandler;

    void showQuestion(String chosenTopic) throws IOException {
        FileHandlerAlt fileHandler = new FileHandlerAlt(chosenTopic + ".txt");
        topic=chosenTopic;



        //Frage anzeigen
        frage.setText(fileHandler.questions[counter2].getQuestion());
        answer1.setText(fileHandler.questions[counter2].getAnswers(0));
        answer2.setText(fileHandler.questions[counter2].getAnswers(1));
        answer3.setText(fileHandler.questions[counter2].getAnswers(2));
        if(fileHandler.questions[counter2].getAnswers(0).equals(fileHandler.questions[counter2].getAnswers(3))){
            answer1.getStyleClass().add("right");
        }else if(fileHandler.questions[counter2].getAnswers(1).equals(fileHandler.questions[counter2].getAnswers(3))){
            answer1.getStyleClass().add("right");
            answer2.getStyleClass().add("false");
        }else if(fileHandler.questions[counter2].getAnswers(2).equals(fileHandler.questions[counter2].getAnswers(3))){
            answer1.getStyleClass().add("right");
            answer3.getStyleClass().add("false");
        }


    }

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        if (event.getSource() == zuruck) {
            if(counter2==0){
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
            } else{
                counter2--;
                counter--;
                showQuestion(topic);
            }



        } else if (event.getSource()==weiter){
            ++counter;
            ++counter2;
            if (counter == 10){
                Stage stage1 = (Stage) frage.getScene().getWindow();

                Stage stage2 = new Stage();
                FXMLLoader fxmlloader = new FXMLLoader();
                stage2.initStyle(StageStyle.UNDECORATED);
                Pane root = fxmlloader.load(getClass().getResource("geschichtequiz3.fxml").openStream());
                Scene scene = new Scene(root, 400, 600);
                stage2.setScene(scene);
                scene.getStylesheets().add(getClass().getResource("style3.css").toExternalForm());
                stage2.setResizable(false);
                stage2.show();
                stage1.close();
            } else {
                resettextfields();
                showQuestion(topic);
            }
        }
    }

    private  void resettextfields(){
        answer3.getStyleClass().clear();
        answer3.getStyleClass().add("text-field");
        answer2.getStyleClass().clear();
        answer2.getStyleClass().add("text-field");
        answer1.getStyleClass().clear();
        answer1.getStyleClass().add("text-field");
    }

}
