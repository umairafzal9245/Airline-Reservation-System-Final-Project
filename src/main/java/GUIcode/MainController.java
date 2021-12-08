package GUIcode;

import BusinessLogic.FlightReservationSystem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;

import java.io.IOException;
import java.util.Optional;

public class MainController {

    public static Scene loginscene;
    public static Scene customerloginscene;
    public static final FlightReservationSystem flightReservationSystem = new FlightReservationSystem();

    @FXML
    void InvokeAdminFunctions(ActionEvent event) throws IOException {
        if(MainController.flightReservationSystem.getAdmin().isLogin() == false)
        {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("LoginPage.fxml"));
            loginscene = new Scene(fxmlLoader.load(), 500, 500);
            HelloApplication.window.setScene(loginscene);
            HelloApplication.window.show();
        }
    }

    @FXML
    void InvokeCustomerFunctions(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("CustomerLogin.fxml"));
        customerloginscene = new Scene(fxmlLoader.load(), 500, 500);
        HelloApplication.window.setScene(customerloginscene);
        HelloApplication.window.show();
    }

    @FXML
    void Exittheprogram(ActionEvent event) {
        Alert newalert = new Alert(Alert.AlertType.CONFIRMATION);
        newalert.setTitle("Confirmation");
        newalert.setContentText("Are you sure");
        Optional<ButtonType> input = newalert.showAndWait();
        if(input.get() == ButtonType.OK)
        {
            HelloApplication.window.close();
        }
    }
}
