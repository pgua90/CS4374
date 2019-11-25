package CSWT;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

public class CSWT extends Application {


    private static Scene scene;
    private static Stage stage;

    @Override
    public void start(Stage stage) throws IOException {
        CSWT.stage = stage;
        changeScene("login");
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CSWT.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void changeScene(String scene) throws IOException {
        CSWT.scene = new Scene(loadFXML(scene));
        stage.setScene(CSWT.scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
