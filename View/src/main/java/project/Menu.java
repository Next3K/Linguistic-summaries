package project;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Menu extends Application {


    @Override
    public void start(Stage primaryStage) throws IOException {
        URL url = getClass().getClassLoader().getResource("UserPanel.fxml");
        StageController.createFirstStage(primaryStage, url,"hemlo");
    }

    public static void main(String[] args) {
        launch();
    }
}