package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static LogHandler logHandler;

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlloader = new FXMLLoader();
        Parent root = FXMLLoader.load(getClass().getResource("GUI/hauptmenu.fxml"));
        Scene scene = new Scene(root, 400, 600);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        scene.getStylesheets().add(getClass().getResource("GUI/style.css").toExternalForm());
        primaryStage.show();
        logHandler = new LogHandler();
        logHandler.checkForNewFiles();
    }


    public static void main(String[] args) {launch(args);}
}