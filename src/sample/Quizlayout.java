package sample;

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
import java.util.ArrayList;
import java.util.Random;

public class Quizlayout {

        @FXML
        private Button answer1;

        @FXML
        private Button answer2;

        @FXML
        private Button answer3;

        @FXML
        private Label frage;

        @FXML
        private Button next;

        private String topic;
        private int rand_int1;
        private int counter = 0;

        //ArrayList zum Speichern der bereits angezeigten Fragen
        private ArrayList<Question> duplicate = new ArrayList<>();


        void randomizeQuestion(String chosenTopic) throws IOException {
                next.setDisable(true);
                FileHandler fileHandler = new FileHandler(chosenTopic + ".txt");
                fileHandler.init();
                topic = chosenTopic;


                Random rand = new Random();
                //Auswählen einer Zufälligen Frage
                rand_int1 = rand.nextInt(fileHandler.questions.length);

                //Überprüfen ob die Frage bereits angezeigt wurde
                while (duplicate.contains(fileHandler.questions[rand_int1])){
                        rand_int1 = rand.nextInt(fileHandler.questions.length);
                }
                //Frage anzeigen
                frage.setText(fileHandler.questions[rand_int1].getQuestion());
                //Frage zum duplicateArray hinzufügen
                duplicate.add(fileHandler.questions[rand_int1]);

                //Antworten werden zufällig zugeordnet
                answer1.setText(fileHandler.questions[rand_int1].getAnswers(rand.nextInt(3)));
                answer2.setText(fileHandler.questions[rand_int1].getAnswers(rand.nextInt(3)));
                while(answer1.getText().equals(answer2.getText())){
                        answer2.setText(fileHandler.questions[rand_int1].getAnswers(rand.nextInt(3)));
                }
                answer3.setText(fileHandler.questions[rand_int1].getAnswers(rand.nextInt(3)));
                while (answer3.getText().equals(answer1.getText())||answer3.getText().equals(answer2.getText())){
                        answer3.setText(fileHandler.questions[rand_int1].getAnswers(rand.nextInt(3)));
                }

        }





        public void handleButtonAction(ActionEvent actionEvent) throws IOException{
                Button clickedButton = (Button) actionEvent.getTarget();
                String buttonlabel = clickedButton.getText();
                FileHandler filehandler = new FileHandler(topic + ".txt");

                if(buttonlabel.equals(filehandler.getAnswer(rand_int1, 0))){
                        clickedButton.getStyleClass().add("right");
                        disablebutton();
                        next.setDisable(false);
                } else if(actionEvent.getSource()==next){
                        randomizeQuestion(topic);
                        resetbuttons();
                        ++counter;
                        ablebutton();
                } else{
                        clickedButton.getStyleClass().add("false");
                        disablebutton();
                        next.setDisable(false);
                }

                 if (counter == 10){
                         Stage stage1 = (Stage) frage.getScene().getWindow();

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


        private void disablebutton(){
                answer1.setDisable(true);
                answer2.setDisable(true);
                answer3.setDisable(true);
        }

        private void ablebutton(){
                answer1.setDisable(false);
                answer2.setDisable(false);
                answer3.setDisable(false);
        }

        private  void resetbuttons(){
                answer3.getStyleClass().clear();
                answer3.getStyleClass().add("button");
                answer2.getStyleClass().add("button:hover");
                answer2.getStyleClass().clear();
                answer2.getStyleClass().add("button");
                answer1.getStyleClass().add("button:hover");
                answer1.getStyleClass().clear();
                answer1.getStyleClass().add("button");
                answer1.getStyleClass().add("button:hover");
        }


}


