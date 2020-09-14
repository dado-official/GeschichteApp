package sample.GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sample.FileHandler;
import sample.Main;
import sample.Question;


import java.io.*;
import java.nio.charset.StandardCharsets;
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
        private ArrayList<Integer> duplicate = new ArrayList<Integer>();
        private Random rand = new Random();
        private FileHandler filehandler;
        int richtig = 0, falsch = 0;
        boolean firstquestion = true;

        void startAttempt() throws IOException {
                if (firstquestion){
                        Main.logHandler.newAttempt(topic);
                        firstquestion = false;
                }
        }


        void randomizeQuestion(String chosenTopic) throws IOException {
                next.setDisable(true);
                FileHandler fileHandler = new FileHandler(chosenTopic + ".txt");
                topic = chosenTopic;
                startAttempt();

                //Auswählen einer Zufälligen Frage
                rand_int1 = rand.nextInt(fileHandler.questions.length);

                //Überprüfen ob die Frage bereits angezeigt wurde
                while (duplicate.contains(rand_int1)){
                        rand_int1 = rand.nextInt(fileHandler.questions.length);
                }

                Main.logHandler.writeQuestionAndAnswersToAttempt(fileHandler.questions[rand_int1], topic);
                //Frage anzeigen
                frage.setText(fileHandler.questions[rand_int1].getQuestion());
                //Frage zum duplicateArray hinzufügen
                duplicate.add(rand_int1);

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

                //Angeklickte Antwort in Attempt File schreiben
                if(actionEvent.getSource() != next){
                        Main.logHandler.writeClickedAnswer(buttonlabel, topic);
                }

                filehandler = new FileHandler(topic + ".txt");

                if(buttonlabel.equals(filehandler.getAnswer(rand_int1, 0))){
                        clickedButton.getStyleClass().add("right");
                        disablebutton();
                        next.setDisable(false);
                        ++richtig;
                } else if(actionEvent.getSource()==next){
                        ++counter;
                        if (counter == 10){
                                Stage stage = (Stage) frage.getScene().getWindow();
                                FXMLLoader fxmlloader = new FXMLLoader();
                                Pane root = fxmlloader.load(getClass().getResource("zusammenfassung.fxml").openStream());
                                Scene scene = new Scene(root, 400, 600);
                                stage.setScene(scene);
                                scene.getStylesheets().add(getClass().getResource("zusammenfassung.css").toExternalForm());
                                Zusammenfassung controller = fxmlloader.getController();
                                controller.showLabelContext(richtig, falsch);
                        } else {
                                randomizeQuestion(topic);
                                resetbuttons();
                                ablebutton();
                        }

                } else{
                        safeWrongAnswer(filehandler.questions[rand_int1]);
                        clickedButton.getStyleClass().add("false");
                        disablebutton();
                        next.setDisable(false);
                        ++falsch;
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

        private void safeWrongAnswer(Question question){
                try
                {
                        String filename= "Themenbereiche/wrongAnswers.txt";
                        OutputStreamWriter fw = new OutputStreamWriter(new FileOutputStream(filename,true), StandardCharsets.UTF_8.name());
                        fw.write(question.getQuestion()+"\n");
                        fw.write(question.getAnswers(0)+"\n");
                        fw.write(question.getAnswers(1)+"\n");
                        fw.write(question.getAnswers(2)+"\n");
                        fw.close();
                }
                catch(IOException ioe)
                {
                        System.err.println("IOException: " + ioe.getMessage());
                }
        } 

}


