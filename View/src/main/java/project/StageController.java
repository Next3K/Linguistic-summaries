package project;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StageController {

    private static Stage stage;

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        StageController.stage = stage;
    }

    private static Parent loadFxml(URL fxml) throws IOException {
        return new FXMLLoader(fxml).load();
    }

    public static void generateNewStage(URL filePath) throws IOException {
        stage.setScene(new Scene(loadFxml(filePath)));
        stage.sizeToScene();
        stage.setResizable(false);
        stage.show();
    }

    public static void generateNewStage(URL filePath,
                                        String title) throws IOException {
        stage.setScene(new Scene(loadFxml(filePath)));
        stage.setTitle(title);
        stage.sizeToScene();
        stage.setResizable(false);
        stage.show();
    }

    public static void createFirstStage(
            Stage stage, URL filePath, String title) throws IOException {
        setStage(stage);
        stage.setScene(new Scene(loadFxml(filePath)));
        stage.setTitle(title);
        stage.sizeToScene();
        stage.setResizable(false);
        stage.show();
    }

}
