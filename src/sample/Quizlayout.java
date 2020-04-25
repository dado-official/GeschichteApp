package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

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
        private TextField frage;

        //ArrayList zum Speichern der bereits angezeigten Fragen
        private ArrayList<Question> duplicate = new ArrayList<Question>();

        public void randomizeQuestion (String chosenTopic) throws IOException {

                FileHandler fileHandler = new FileHandler(chosenTopic + ".txt");
                fileHandler.init();

                Random rand = new Random();
                //Auswählen einer Zufälligen Frage
                int rand_int1 = rand.nextInt(fileHandler.questions.length);

                //Überprüfen ob die Frage bereits angezeigt wurde
                for(int i = 0; i < duplicate.size(); i++){
                        if(fileHandler.questions[rand_int1] == duplicate.get(i)){
                                rand_int1 = rand.nextInt(fileHandler.questions.length);
                                i = 0;
                        }
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
}


