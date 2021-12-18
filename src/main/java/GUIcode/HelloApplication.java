package GUIcode;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;


public class HelloApplication extends Application
{
    private static Stage window;
    private Scene SplashScene;

    @Override
    public void start(Stage stage) throws IOException
    {
        setWindow(stage);
        getWindow().initStyle(StageStyle.UNDECORATED);
        getWindow().setResizable(false);
        getWindow().setWidth(500);
        getWindow().setHeight(300);
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("SplashScreen.fxml"));
        setSplashScene(new Scene(fxmlLoader.load(),500,300));
        getWindow().setTitle("Welcome to Airline Reservation System");
        getWindow().setScene(getSplashScene());
        getWindow().show();
    }

    public static void main(String[] args) {
        launch(HelloApplication.class);
    }

    public static Stage getWindow() {
        return window;
    }

    public static void setWindow(Stage window) {
        HelloApplication.window = window;
    }

    public Scene getSplashScene() {
        return SplashScene;
    }

    public void setSplashScene(Scene splashScene) {
        SplashScene = splashScene;
    }
}