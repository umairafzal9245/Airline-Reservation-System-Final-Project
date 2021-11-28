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

    static Scene Adminsfunctionscene;
    static FlightReservationSystem flightReservationSystem = new FlightReservationSystem();
    @FXML
    private Button MainAdminButton;

    @FXML
    private Button MainCustomerbutton;

    @FXML
    private Label welcomeText;

    @FXML
    private Button ExitButton;

    @FXML
    void InvokeAdminFunctions(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("AdminFunctionsList.fxml"));
        Adminsfunctionscene = new Scene(fxmlLoader.load(), 500, 500);
        HelloApplication.window.setScene(Adminsfunctionscene);
    }

    @FXML
    void InvokeCustomerFunctions(ActionEvent event) {
        System.out.println("Customer clicked");
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
