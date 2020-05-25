package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {
    public static LogHandler logHandler;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("GUI/hauptmenu.fxml"));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        Scene scene = new Scene(root, 400, 600);
        primaryStage.setScene(scene);
        scene.getStylesheets().add(getClass().getResource("GUI/style.css").toExternalForm());
        primaryStage.setResizable(false);
        primaryStage.show();
        logHandler = new LogHandler();
        logHandler.checkForNewFiles();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
