package sample;

import scenes.SceneManager;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        primaryStage.setTitle("Gestion Magasin");
        SceneManager sceneManager=new SceneManager(primaryStage);
        sceneManager.goToHomeScene(sceneManager);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
