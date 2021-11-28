package GUIcode;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    static Stage window;
    static Scene MainMenu;
    @Override
    public void start(Stage stage) throws IOException {
        window = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("MainMenu.fxml"));
        MainMenu = new Scene(fxmlLoader.load(), 500, 500);
        window.setTitle("Welcome to Airline Reservation System");
        window.setScene(MainMenu);
        window.show();
    }

    public static void main(String[] args) {
        launch();
    }
}