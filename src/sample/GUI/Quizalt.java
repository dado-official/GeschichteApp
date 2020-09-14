package sample.GUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sample.FileHandlerAlt;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class Quizalt {
    private File[] listOfFiles;

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
    private int counter;
    private int counter2=0;
    private String topic;

    //ArrayList zum Speichern der bereits angezeigten Fragen

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
                File folder = new File("Quiz");
                listOfFiles = folder.listFiles();
                if(listOfFiles == null || listOfFiles.length==0){
                    JOptionPane.showMessageDialog(null,"Noch keine Quiz gemacht");
                } else {
                    Stage stage = (Stage) weiter.getScene().getWindow();
                    MenuButton altequiz = new MenuButton("Altes Quiz auswaehlen");
                    altequiz.setPrefSize(200, 50);
                    //i = anzahl der Themebereiche

                    assert listOfFiles != null;
                    System.out.println(listOfFiles[0].getName());


                    System.out.println(Arrays.toString(listOfFiles));
                    MenuItem[] items = new MenuItem[listOfFiles.length];

                    for (int i = 0; i < listOfFiles.length; i++) {
                        System.out.println(listOfFiles[i].getName());
                        String tmp = listOfFiles[i].getName();
                        String[] strarr = tmp.split(".txt");
                        items[i] = new MenuItem(strarr[0]);

                        items[i].setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                MenuItem clickedMenuItem = (MenuItem) event.getTarget();
                                String buttonlabel = clickedMenuItem.getText();

                                File f = new File("Themenbereiche");


                                String chosenTopic = buttonlabel;
                                try {
                                    FileHandlerAlt fileHandler = new FileHandlerAlt(chosenTopic + ".txt");
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                                Stage stage = (Stage) altequiz.getScene().getWindow();
                                FXMLLoader fxmlloader = new FXMLLoader();
                                Pane root = null;
                                try {
                                    root = fxmlloader.load(getClass().getResource("quizalt.fxml").openStream());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                Scene scene = new Scene(root, 400, 600);
                                stage.setScene(scene);
                                scene.getStylesheets().add(getClass().getResource("quizalt.css").toExternalForm());
                                Quizalt controller = fxmlloader.getController();
                                try {
                                    controller.showQuestion(chosenTopic);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }

                        });


                    }
                    altequiz.getItems().addAll(items);
                    Button zuruck = new Button("zurück");
                    zuruck.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            Stage stage = (Stage) zuruck.getScene().getWindow();
                            FXMLLoader fxmlloader = new FXMLLoader();
                            Pane root = null;
                            try {
                                root = fxmlloader.load(getClass().getResource("hauptmenu.fxml").openStream());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            Scene scene = new Scene(root, 400, 600);
                            stage.setScene(scene);
                            scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

                        }
                    });
                    zuruck.setPrefSize(100, 25);
                    GridPane tabel = new GridPane();
                    tabel.add(altequiz, 0, 0);
                    tabel.setAlignment(Pos.CENTER);
                    BorderPane root = new BorderPane();
                    root.setCenter(tabel);
                    root.setBottom(zuruck);
                    root.setPadding(new Insets(0, 0, 25, 25));
                    root.autosize();
                    Scene scene = new Scene(root, 400, 600);
                    stage.setScene(scene);
                    scene.getStylesheets().add(getClass().getResource("style3.css").toExternalForm());
                }
            } else{
                counter2--;
                counter--;
                showQuestion(topic);
            }



        } else if (event.getSource()==weiter){
            ++counter;
            ++counter2;
            if (counter == 10){
                File folder = new File("Quiz");
                listOfFiles = folder.listFiles();
                if(listOfFiles == null || listOfFiles.length==0){
                    JOptionPane.showMessageDialog(null,"Noch keine Quiz gemacht");
                } else {
                    Stage stage = (Stage) weiter.getScene().getWindow();
                    MenuButton altequiz = new MenuButton("Altes Quiz auswaehlen");
                    altequiz.setPrefSize(200, 50);
                    //i = anzahl der Themebereiche

                    assert listOfFiles != null;
                    System.out.println(listOfFiles[0].getName());


                    System.out.println(Arrays.toString(listOfFiles));
                    MenuItem[] items = new MenuItem[listOfFiles.length];

                    for (int i = 0; i < listOfFiles.length; i++) {
                        System.out.println(listOfFiles[i].getName());
                        String tmp = listOfFiles[i].getName();
                        String[] strarr = tmp.split(".txt");
                        items[i] = new MenuItem(strarr[0]);

                        items[i].setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                MenuItem clickedMenuItem = (MenuItem) event.getTarget();
                                String buttonlabel = clickedMenuItem.getText();

                                File f = new File("Themenbereiche");


                                String chosenTopic = buttonlabel;
                                try {
                                    FileHandlerAlt fileHandler = new FileHandlerAlt(chosenTopic + ".txt");
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                                Stage stage = (Stage) altequiz.getScene().getWindow();
                                FXMLLoader fxmlloader = new FXMLLoader();
                                Pane root = null;
                                try {
                                    root = fxmlloader.load(getClass().getResource("quizalt.fxml").openStream());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                Scene scene = new Scene(root, 400, 600);
                                stage.setScene(scene);
                                scene.getStylesheets().add(getClass().getResource("quizalt.css").toExternalForm());
                                Quizalt controller = fxmlloader.getController();
                                try {
                                    controller.showQuestion(chosenTopic);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }

                        });


                    }
                    altequiz.getItems().addAll(items);
                    Button zuruck = new Button("zurück");
                    zuruck.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            Stage stage = (Stage) zuruck.getScene().getWindow();
                            FXMLLoader fxmlloader = new FXMLLoader();
                            Pane root = null;
                            try {
                                root = fxmlloader.load(getClass().getResource("hauptmenu.fxml").openStream());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            Scene scene = new Scene(root, 400, 600);
                            stage.setScene(scene);
                            scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

                        }
                    });
                    zuruck.setPrefSize(100, 25);
                    GridPane tabel = new GridPane();
                    tabel.add(altequiz, 0, 0);
                    tabel.setAlignment(Pos.CENTER);
                    BorderPane root = new BorderPane();
                    root.setCenter(tabel);
                    root.setBottom(zuruck);
                    root.setPadding(new Insets(0, 0, 25, 25));
                    root.autosize();
                    Scene scene = new Scene(root, 400, 600);
                    stage.setScene(scene);
                    scene.getStylesheets().add(getClass().getResource("style3.css").toExternalForm());
                }
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
