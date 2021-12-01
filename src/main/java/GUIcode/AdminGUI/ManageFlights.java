package GUIcode.AdminGUI;

import GUIcode.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

public class ManageFlights {

    public static Scene ShowAllFlightscene;
    @FXML
    void AddNewFlight(ActionEvent event) {

    }

    @FXML
    void BackToMenu(ActionEvent event) {
        HelloApplication.window.setScene(LoginPage.Adminsfunctionscene);
        HelloApplication.window.show();
    }

    @FXML
    void DeleteFlight(ActionEvent event) {

    }

    @FXML
    void ShowAllFlights(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ShowAllFlights.fxml"));
        ShowAllFlightscene = new Scene(fxmlLoader.load(), 500, 500);
        HelloApplication.window.setScene(ShowAllFlightscene);
        HelloApplication.window.show();
    }

}
