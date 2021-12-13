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
    private static Scene MainMenu;
    @Override
    public void start(Stage stage) throws IOException
    {
        setWindow(stage);
        getWindow().setResizable(false);
        getWindow().setWidth(500);
        getWindow().setHeight(440);
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("MainMenu.fxml"));
        setMainMenu(new Scene(fxmlLoader.load(),500,400));
        getWindow().setTitle("Welcome to Airline Reservation System");
        getWindow().setScene(getMainMenu());
        getWindow().show();
    }

    public static void main(String[] args) {
        launch();
    }
    public static Stage getWindow() {
        return window;
    }

    public static void setWindow(Stage window) {
        HelloApplication.window = window;
    }

    public static Scene getMainMenu() {
        return MainMenu;
    }

    public static void setMainMenu(Scene mainMenu) {
        MainMenu = mainMenu;
    }
}