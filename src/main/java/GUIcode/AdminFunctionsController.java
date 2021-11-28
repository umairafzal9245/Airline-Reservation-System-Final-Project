package GUIcode;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;


import java.io.IOException;

public class AdminFunctionsController {

    static Scene loginscene;
    @FXML
    private Button ExitMainMenu;

    @FXML
    private Button LoginAccount;

    @FXML
    private Button LogoutAccount;

    @FXML
    private Button ManageFlights;

    @FXML
    private Button RegisterAccount;

    @FXML
    private Button ViewAdminDetails;

    @FXML
    void ExitMainMenu(ActionEvent event) {
        HelloApplication.window.setScene(HelloApplication.MainMenu);
        HelloApplication.window.show();
    }

    @FXML
    void LoginAdminAccount(ActionEvent event) throws IOException {
        if(MainController.flightReservationSystem.admin.isLogin())
        {
            Alert message = new Alert(Alert.AlertType.INFORMATION);
            message.setTitle("Login");
            message.setContentText("You are already logged in");
            message.showAndWait();
        }
        else {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("LoginPage.fxml"));
            loginscene = new Scene(fxmlLoader.load(), 500, 500);
            HelloApplication.window.setScene(loginscene);
            HelloApplication.window.show();
        }
    }

    @FXML
    void LogoutAdminAccount(ActionEvent event) {

    }

    @FXML
    void ManageFlightSchedules(ActionEvent event) {

    }

    @FXML
    void RegisterAdminAccount(ActionEvent event) {

    }

    @FXML
    void ViewAdminDetails(ActionEvent event) {

    }

}
