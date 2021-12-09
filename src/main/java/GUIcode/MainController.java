package GUIcode;

import BusinessLogic.FlightReservationSystem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.io.IOException;
import java.util.Optional;

public class MainController {

    private static Scene loginscene;
    private static Scene customerloginscene;
    private static final FlightReservationSystem flightReservationSystem = new FlightReservationSystem();

    @FXML
    void InvokeAdminFunctions(ActionEvent event) throws IOException {
        if(MainController.getFlightReservationSystem().getAdmin().isLogin() == false)
        {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("LoginPage.fxml"));
            setLoginscene(new Scene(fxmlLoader.load(), 500, 500));
            HelloApplication.getWindow().setScene(getLoginscene());
            HelloApplication.getWindow().show();
        }
    }

    @FXML
    void InvokeCustomerFunctions(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("CustomerLogin.fxml"));
        setCustomerloginscene(new Scene(fxmlLoader.load(), 500, 500));
        HelloApplication.getWindow().setScene(getCustomerloginscene());
        HelloApplication.getWindow().show();
    }

    @FXML
    void Exittheprogram(ActionEvent event) {
        Alert newalert = new Alert(Alert.AlertType.CONFIRMATION);
        newalert.setTitle("Confirmation");
        newalert.setContentText("Are you sure");
        Optional<ButtonType> input = newalert.showAndWait();
        if(input.get() == ButtonType.OK)
        {
            HelloApplication.getWindow().close();
        }
    }
    public static Scene getLoginscene() {
        return loginscene;
    }

    public static void setLoginscene(Scene loginscene) {
        MainController.loginscene = loginscene;
    }

    public static Scene getCustomerloginscene() {
        return customerloginscene;
    }

    public static void setCustomerloginscene(Scene customerloginscene) {
        MainController.customerloginscene = customerloginscene;
    }

    public static FlightReservationSystem getFlightReservationSystem() {
        return flightReservationSystem;
    }
}
