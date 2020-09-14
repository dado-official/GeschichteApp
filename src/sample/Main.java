package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {
    public static LogHandler logHandler;
    private  double xOffset = 0;
    private  double yOffset = 0;

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlloader = new FXMLLoader();
        Parent root = FXMLLoader.load(getClass().getResource("GUI/hauptmenu.fxml"));
        Scene scene = new Scene(root, 400, 600);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.initStyle(StageStyle.UNDECORATED);
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
                primaryStage.setX(event.getScreenX() - xOffset);
                primaryStage.setY(event.getScreenY() - yOffset);
            }
        });
        scene.getStylesheets().add(getClass().getResource("GUI/style.css").toExternalForm());
        primaryStage.show();
        logHandler = new LogHandler();
        logHandler.checkForNewFiles();
    }


    public static void main(String[] args) {launch(args);}
}