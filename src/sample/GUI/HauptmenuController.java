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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sample.FileHandlerAlt;
import sample.LogHandler;

import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
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
    private Button offnen;
    @FXML
    private Button reset;

    private  double xOffset = 0;
    private  double yOffset = 0;
    public static LogHandler logHandler;



    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        if (event.getSource() == close) {
            Stage stage = (Stage) neu.getScene().getWindow();

            FXMLLoader fxmlloader = new FXMLLoader();
            Pane root = fxmlloader.load(getClass().getResource("quit.fxml").openStream());
            Scene scene = new Scene(root, 400, 600);
            root.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    xOffset = event.getSceneX();
                    yOffset = event.getSceneY();
                }
            });
            root.setOnMouseDragged(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    stage.setX(event.getScreenX() - xOffset);
                    stage.setY(event.getScreenY() - yOffset);
                }
            });
            stage.setScene(scene);
            scene.getStylesheets().add(getClass().getResource("stylequit.css").toExternalForm());
        } else if (event.getSource() == neu) {
            File folder = new File("Themenbereiche");
            listOfFiles = folder.listFiles();

            assert listOfFiles != null;
            System.out.println(Arrays.toString(listOfFiles));
            Stage stage = (Stage) neu.getScene().getWindow();
            MenuItem[] items = new MenuItem[listOfFiles.length];

            Button aktuellesthema = new Button("Aktuelles Thema");
            aktuellesthema.setPrefSize(200,50);
            aktuellesthema.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    String tmp = null;
                    try {
                        tmp = getLatestFilefromDir().getName();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    String[] tmpstr = tmp.split(".txt");
                    int count = 0;

                    try {
                        if(tmpstr[0].equals("wrongAnswers"))
                            count = countlines("1 Weltkrieg 1915");
                        else{
                            count = countlines(tmpstr[0]);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    if(count>=40){
                        Stage stage = (Stage) aktuellesthema.getScene().getWindow();
                        FXMLLoader fxmlloader = new FXMLLoader();
                        Pane root = null;
                        try {
                            root = fxmlloader.load(getClass().getResource("quizlayout.fxml").openStream());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Scene scene = new Scene(root, 400, 600);
                        root.setOnMousePressed(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {
                                xOffset = event.getSceneX();
                                yOffset = event.getSceneY();
                            }
                        });
                        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {
                                stage.setX(event.getScreenX() - xOffset);
                                stage.setY(event.getScreenY() - yOffset);
                            }
                        });
                        stage.setScene(scene);
                        scene.getStylesheets().add(getClass().getResource("quizstyle.css").toExternalForm());
                        Quizlayout controller = fxmlloader.<Quizlayout>getController();
                        tmp = null;
                        try {
                            tmp = getLatestFilefromDir().getName();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        tmpstr = tmp.split(".txt");

                        try {
                            if(tmpstr[0].equals("wrongAnswers"))
                                controller.randomizeQuestion("1 Weltkrieg 1915");
                            else{
                                controller.randomizeQuestion(tmpstr[0]);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "Nicht genügend Fragen");
                    }

                }





            });
            Button fehler = new Button("Quiz mit letzten Fehler");
            fehler.setPrefSize(200,50);
            fehler.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    int count = 0;
                    try {
                        count = countlines("wrongAnswers");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if(count>=40){
                        Stage stage = (Stage) fehler.getScene().getWindow();
                        FXMLLoader fxmlloader = new FXMLLoader();
                        Pane root = null;
                        try {
                            root = fxmlloader.load(getClass().getResource("quizlayout.fxml").openStream());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Scene scene = new Scene(root, 400, 600);
                        root.setOnMousePressed(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {
                                xOffset = event.getSceneX();
                                yOffset = event.getSceneY();
                            }
                        });
                        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {
                                stage.setX(event.getScreenX() - xOffset);
                                stage.setY(event.getScreenY() - yOffset);
                            }
                        });
                        stage.setScene(scene);
                        scene.getStylesheets().add(getClass().getResource("quizstyle.css").toExternalForm());
                        Quizlayout controller = fxmlloader.<Quizlayout>getController();
                        try {
                            controller.randomizeQuestion("wrongAnswers");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "Noch nicht genügend Fehler");
                    }

                }
            });
            Button zuruck = new Button("Zurück");
            zuruck.setMaxWidth(100);
            zuruck.setMaxHeight(25);
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
                    root.setOnMousePressed(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            xOffset = event.getSceneX();
                            yOffset = event.getSceneY();
                        }
                    });
                    root.setOnMouseDragged(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            stage.setX(event.getScreenX() - xOffset);
                            stage.setY(event.getScreenY() - yOffset);
                        }
                    });
                    stage.setScene(scene);
                    scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
                }
            });
            MenuButton bestthema = new MenuButton("Quiz zu bestimmten Thema");

            bestthema.setPrefSize(200,50);
            File log = new File("log/log.txt");
            BufferedReader bufferedReader = new BufferedReader(new FileReader(log));
            //i = anzahl der Themebereiche
            String[] line = new String[listOfFiles.length];

            for (int i = 0; i < listOfFiles.length; i++) {
                String tmp = listOfFiles[i].getName();
                String[] strarr = tmp.split(".txt");
                items[i] = new MenuItem(strarr[0]);

                items[i].setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        MenuItem clickedMenuItem = (MenuItem) event.getTarget();
                        String buttonlabel = clickedMenuItem.getText();

                        String chosenTopic = buttonlabel;
                        int count = 0;
                        try {
                            count = countlines(chosenTopic);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        if (count >= 40) {


                            Stage stage = (Stage) aktuellesthema.getScene().getWindow();
                            FXMLLoader fxmlloader = new FXMLLoader();
                            Pane root = null;
                            try {
                                root = fxmlloader.load(getClass().getResource("quizlayout.fxml").openStream());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            Scene scene = new Scene(root, 400, 600);
                            root.setOnMousePressed(new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent event) {
                                    xOffset = event.getSceneX();
                                    yOffset = event.getSceneY();
                                }
                            });
                            root.setOnMouseDragged(new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent event) {
                                    stage.setX(event.getScreenX() - xOffset);
                                    stage.setY(event.getScreenY() - yOffset);
                                }
                            });
                            stage.setScene(scene);
                            scene.getStylesheets().add(getClass().getResource("quizstyle.css").toExternalForm());
                            Quizlayout controller = fxmlloader.<Quizlayout>getController();
                            try {
                                controller.randomizeQuestion(chosenTopic);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }else{
                            JOptionPane.showMessageDialog(null, "Nicht genügend Fragen");
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
            tabelle.autosize();
            BorderPane root =new BorderPane();
            root.setPadding(new Insets(0,0,25,25));
            root.setBottom(zuruck);
            root.setCenter(tabelle);
            root.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    xOffset = event.getSceneX();
                    yOffset = event.getSceneY();
                }
            });
            root.setOnMouseDragged(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    stage.setX(event.getScreenX() - xOffset);
                    stage.setY(event.getScreenY() - yOffset);
                }
            });
            Scene scene = new Scene(root, 400, 600);
            stage.setScene(scene);
            scene.getStylesheets().add(getClass().getResource("style2.css").toExternalForm());

        } else if(event.getSource() == alt){
            File folder = new File("Quiz");
            listOfFiles = folder.listFiles();
            if(listOfFiles == null || listOfFiles.length==0){
                JOptionPane.showMessageDialog(null,"Noch keine Quiz gemacht");
            } else {
                Stage stage = (Stage) neu.getScene().getWindow();
                MenuButton altequiz = new MenuButton("Altes Quiz auswählen");
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
                            root.setOnMousePressed(new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent event) {
                                    xOffset = event.getSceneX();
                                    yOffset = event.getSceneY();
                                }
                            });
                            root.setOnMouseDragged(new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent event) {
                                    stage.setX(event.getScreenX() - xOffset);
                                    stage.setY(event.getScreenY() - yOffset);
                                }
                            });
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
                        root.setOnMousePressed(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {
                                xOffset = event.getSceneX();
                                yOffset = event.getSceneY();
                            }
                        });
                        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {
                                stage.setX(event.getScreenX() - xOffset);
                                stage.setY(event.getScreenY() - yOffset);
                            }
                        });
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
                root.setOnMousePressed(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        xOffset = event.getSceneX();
                        yOffset = event.getSceneY();
                    }
                });
                root.setOnMouseDragged(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        stage.setX(event.getScreenX() - xOffset);
                        stage.setY(event.getScreenY() - yOffset);
                    }
                });
                Scene scene = new Scene(root, 400, 600);
                stage.setScene(scene);
                scene.getStylesheets().add(getClass().getResource("style3.css").toExternalForm());
            }
        }else if(event.getSource()==offnen){
            java.awt.Desktop.getDesktop().browse(new File("Themenbereiche").toURI());
        }else if(event.getSource()==reset){
            File f = new File("Quiz");
            deleteDir(f);
            PrintWriter pw = new PrintWriter("Themenbereiche\\wrongAnswers.txt");
            pw.close();
            PrintWriter pw2 = new PrintWriter("log\\log.txt");
            pw2.close();
            logHandler = new LogHandler();
            logHandler.checkForNewFiles();



        }
    }



    public void menuClickHandler(ActionEvent actionEvent) throws IOException {
        MenuItem clickedMenu = (MenuItem) actionEvent.getTarget();
        String menuLabel = clickedMenu.getText();

        if("Infos".equals(menuLabel)){
            JOptionPane.showMessageDialog(null,"Programmiert von Daniel Nagler, Silas Keim, Sebastian Hofer\n" + "Im Auftrag von Meinhard Mair","Infos", JOptionPane.PLAIN_MESSAGE);
        }
    }

    private File getLatestFilefromDir() throws IOException {
        File dir = new File("Themenbereiche");
        File[] files=dir.listFiles();
        if(files == null || files.length==0){
            return null;
        }
        File lastcreatedfile = files[0];
        Path path = lastcreatedfile.toPath();
        BasicFileAttributes fatr= Files.readAttributes(path, BasicFileAttributes.class);
        for(int i =1;i<files.length;i++){
            Path path1 = files[i].toPath();
            path = lastcreatedfile.toPath();
            fatr= Files.readAttributes(path, BasicFileAttributes.class);
            BasicFileAttributes fatr1= Files.readAttributes(path1, BasicFileAttributes.class);
            if(fatr.creationTime().compareTo(fatr1.creationTime())<0){
                    lastcreatedfile=files[i];
            }
        }
        return lastcreatedfile;
    }
    private int countlines(String chosentopic) throws IOException {
        int count=0;
        File wrong = new File("Themenbereiche/" + chosentopic + ".txt");
        BufferedReader in = new BufferedReader(new FileReader(wrong));
        while (in.readLine() != null) {
            ++count;
        }
        return count;
    }
    public static void deleteDir(File dir) {

        File[] files = dir.listFiles();
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory()) {
                    deleteDir(files[i]);
                }
                else {
                    files[i].delete();
                }
            }
        }
    }
}