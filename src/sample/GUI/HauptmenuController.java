package sample.GUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.FileHandler;
import sample.FileHandlerAlt;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class HauptmenuController {
    private File[] listOfFiles;
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
            stage2.initStyle(StageStyle.UNDECORATED);
            Pane root = fxmlloader.load(getClass().getResource("quit.fxml").openStream());
            Scene scene = new Scene(root, 400, 600);
            stage2.setScene(scene);
            scene.getStylesheets().add(getClass().getResource("stylequit.css").toExternalForm());
            stage2.setResizable(false);
            stage2.show();
            stage1.close();
        } else if (event.getSource() == neu) {
            File folder = new File("Themenbereiche");
            listOfFiles = folder.listFiles();

            assert listOfFiles != null;
            System.out.println(Arrays.toString(listOfFiles));
            Stage stage1 = (Stage) neu.getScene().getWindow();
            MenuItem[] items = new MenuItem[listOfFiles.length];

            Stage stage2 = new Stage();
            stage2.initStyle(StageStyle.UNDECORATED);
            Button aktuellesthema = new Button("Aktuelles Thema");
            aktuellesthema.setPrefSize(200,50);
            aktuellesthema.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    Stage stage1 = (Stage) aktuellesthema.getScene().getWindow();

                    Stage stage2 = new Stage();
                    FXMLLoader fxmlloader = new FXMLLoader();
                    stage2.initStyle(StageStyle.UNDECORATED);
                    Pane root = null;
                    try {
                        root = fxmlloader.load(getClass().getResource("quizlayout.fxml").openStream());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Scene scene = new Scene(root, 400, 600);
                    stage2.setScene(scene);
                    scene.getStylesheets().add(getClass().getResource("quizstyle.css").toExternalForm());
                    stage2.setResizable(false);
                    stage2.show();
                    stage1.close();
                    Quizlayout controller = fxmlloader.<Quizlayout>getController();
                    try {
                        controller.randomizeQuestion("1 Weltkrieg 1915");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            });
            Button fehler = new Button("Quiz mit letzten Fehler");
            fehler.setPrefSize(200,50);
            fehler.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    Stage stage1 = (Stage) fehler.getScene().getWindow();

                    Stage stage2 = new Stage();
                    FXMLLoader fxmlloader = new FXMLLoader();
                    stage2.initStyle(StageStyle.UNDECORATED);
                    Pane root = null;
                    try {
                        root = fxmlloader.load(getClass().getResource("quizlayout.fxml").openStream());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Scene scene = new Scene(root, 400, 600);
                    stage2.setScene(scene);
                    scene.getStylesheets().add(getClass().getResource("quizstyle.css").toExternalForm());
                    stage2.setResizable(false);
                    stage2.show();
                    stage1.close();
                    Quizlayout controller = fxmlloader.<Quizlayout>getController();
                    try {
                        controller.randomizeQuestion("wrongAnswers");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            Button zuruck = new Button("Zurück");
            zuruck.setMaxWidth(100);
            zuruck.setMaxHeight(25);
            zuruck.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    Stage stage1 = (Stage) zuruck.getScene().getWindow();

                    Stage stage2 = new Stage();
                    FXMLLoader fxmlloader = new FXMLLoader();
                    stage2.initStyle(StageStyle.UNDECORATED);
                    Pane root = null;
                    try {
                        root = fxmlloader.load(getClass().getResource("hauptmenu.fxml").openStream());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Scene scene = new Scene(root, 400, 600);
                    stage2.setScene(scene);
                    scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
                    stage2.setResizable(false);
                    stage2.show();
                    stage1.close();
                }
            });
            MenuButton bestthema = new MenuButton("Quiz zu bestimmten Thema");

            bestthema.setPrefSize(200,50);
            File log = new File("log/log.txt");
            BufferedReader bufferedReader = new BufferedReader(new FileReader(log));
            //i = anzahl der Themebereiche
            String[] line = new String[listOfFiles.length];

            for (int i = 0; i < listOfFiles.length; i++) {
                String tmp;
                line[i] = bufferedReader.readLine();
                tmp = line[i];
                String[] tmpstr= tmp.split(";");
                    items[i]=new MenuItem(tmpstr[0]);

                    items[i].setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            MenuItem clickedMenuItem = (MenuItem) event.getTarget();
                            String buttonlabel = clickedMenuItem.getText();

                            File f = new File("Themenbereiche");


                            String chosenTopic = buttonlabel;
                            try {
                                FileHandler fileHandler = new FileHandler(chosenTopic + ".txt");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            Stage stage1 = (Stage) aktuellesthema.getScene().getWindow();
                            Stage stage2 = new Stage();
                            FXMLLoader fxmlloader = new FXMLLoader();
                            stage2.initStyle(StageStyle.UNDECORATED);
                            Pane root = null;
                            try {
                                root = fxmlloader.load(getClass().getResource("quizlayout.fxml").openStream());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            Scene scene = new Scene(root, 400, 600);
                            stage2.setScene(scene);
                            scene.getStylesheets().add(getClass().getResource("quizstyle.css").toExternalForm());
                            stage2.setResizable(false);
                            stage2.show();
                            stage1.close();
                            Quizlayout controller = fxmlloader.<Quizlayout>getController();
                            try {
                                controller.randomizeQuestion(chosenTopic);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                    });
                }
            bufferedReader.close();
            bestthema.getItems().addAll(items);
            GridPane tabelle = new GridPane();
            GridPane.setMargin(aktuellesthema, new Insets(0,0,25,0));
            GridPane.setMargin(fehler, new Insets(0,0,25,0));
            tabelle.add(aktuellesthema, 0,0);
            tabelle.add(fehler, 0,1);
            tabelle.add(bestthema, 0,2);
            tabelle.setAlignment(Pos.CENTER);
            BorderPane root =new BorderPane();
            root.setPadding(new Insets(0,0,25,25));
            root.setBottom(zuruck);
            root.setCenter(tabelle);
            Scene scene = new Scene(root, 400, 600);
            stage2.setScene(scene);
            scene.getStylesheets().add(getClass().getResource("style2.css").toExternalForm());
            stage2.setResizable(false);
            stage2.show();
            stage1.close();

        } else if(event.getSource() == alt){
            Stage stage1 = (Stage) neu.getScene().getWindow();
            Stage stage2 = new Stage();
            stage2.initStyle(StageStyle.UNDECORATED);
            MenuButton altequiz = new MenuButton("Altes Quiz auswaehlen");
            altequiz.setPrefSize(200,50);
            //i = anzahl der Themebereiche
            File folder = new File("Quiz");
            listOfFiles = folder.listFiles();
            assert listOfFiles != null;
            System.out.println(listOfFiles[0].getName());



            System.out.println(Arrays.toString(listOfFiles));
            MenuItem[] items = new MenuItem[listOfFiles.length];

            for (int i = 0; i < listOfFiles.length; i++) {
                System.out.println(listOfFiles[i].getName());
                String tmp = listOfFiles[i].getName();
                String[] strarr=tmp.split(".txt");
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

                            Stage stage1 = (Stage) altequiz.getScene().getWindow();
                            Stage stage2 = new Stage();
                            FXMLLoader fxmlloader = new FXMLLoader();
                            stage2.initStyle(StageStyle.UNDECORATED);
                            Pane root = null;
                            try {
                                root = fxmlloader.load(getClass().getResource("quizalt.fxml").openStream());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            Scene scene = new Scene(root, 400, 600);
                            stage2.setScene(scene);
                            scene.getStylesheets().add(getClass().getResource("quizalt.css").toExternalForm());
                            stage2.setResizable(false);
                            stage2.show();
                            stage1.close();
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
                    Stage stage1 = (Stage) zuruck.getScene().getWindow();

                    Stage stage2 = new Stage();
                    FXMLLoader fxmlloader = new FXMLLoader();
                    stage2.initStyle(StageStyle.UNDECORATED);
                    Pane root = null;
                    try {
                        root = fxmlloader.load(getClass().getResource("hauptmenu.fxml").openStream());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Scene scene = new Scene(root, 400, 600);
                    stage2.setScene(scene);
                    scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
                    stage2.setResizable(false);
                    stage2.show();
                    stage1.close();
                }
            });
            zuruck.setPrefSize(100,25);
            GridPane tabel = new GridPane();
            tabel.add(altequiz,0,0);
            tabel.setAlignment(Pos.CENTER);
            BorderPane root = new BorderPane();
            root.setCenter(tabel);
            root.setBottom(zuruck);
            root.setPadding(new Insets(0,0,25,25));
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
