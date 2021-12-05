package GUIcode.CustomerGUI;

import GUIcode.HelloApplication;
import GUIcode.MainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.io.IOException;
import java.util.Optional;

public class CustomerFunctions {

    public static Scene displayaccountscene;
    public static Scene searchflightscene;
    public static Scene cancelreservationscene;
    public static Scene bookflight;
    public static Scene showreservations;

    @FXML
    void PrintTicket(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ShowReservations.fxml"));
        showreservations = new Scene(fxmlLoader.load(), 500, 500);
        HelloApplication.window.setScene(showreservations);
        HelloApplication.window.show();
    }

    @FXML
    void BookaFlight(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("BookFlight.fxml"));
        bookflight = new Scene(fxmlLoader.load(), 500, 500);
        HelloApplication.window.setScene(bookflight);
        HelloApplication.window.show();
    }

    @FXML
    void CalculateFares(ActionEvent event) {

    }

    @FXML
    void CancelReservation(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("CancelReservation.fxml"));
        cancelreservationscene = new Scene(fxmlLoader.load(), 500, 500);
        HelloApplication.window.setScene(cancelreservationscene);
        HelloApplication.window.show();
    }

    @FXML
    void SearchFlight(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("SearchFlight.fxml"));
        searchflightscene = new Scene(fxmlLoader.load(), 500, 500);
        HelloApplication.window.setScene(searchflightscene);
        HelloApplication.window.show();
    }

    @FXML
    void DeleteAccount(ActionEvent event) {

        Alert newalert = new Alert(Alert.AlertType.CONFIRMATION);
        newalert.setTitle("Confirmation");
        newalert.setContentText("Your account will be deleted");
        Optional<ButtonType> input = newalert.showAndWait();
        if(input.get() == ButtonType.OK) {
            boolean flag = false;
            try {
                MainController.flightReservationSystem.customers.DeleteCustomer();
                flag = true;
            }
            catch (Exception e)
            {
                Alert message = new Alert(Alert.AlertType.ERROR);
                message.setTitle("Error");
                message.setContentText(e.getMessage());
                message.showAndWait();
            }
            if(flag) {
                Alert message = new Alert(Alert.AlertType.INFORMATION);
                message.setTitle("Deleted ");
                message.setContentText("You has been deleted");
                message.showAndWait();
                HelloApplication.window.setScene(HelloApplication.MainMenu);
                HelloApplication.window.show();
            }
        }
    }

    @FXML
    void DisplayAccountDetails(ActionEvent event) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Displayaccount.fxml"));
        displayaccountscene = new Scene(fxmlLoader.load(), 500, 500);
        HelloApplication.window.setScene(displayaccountscene);
        HelloApplication.window.show();
    }

    @FXML
    void Exit(ActionEvent event) {
        Alert newalert = new Alert(Alert.AlertType.CONFIRMATION);
        newalert.setTitle("Confirmation");
        newalert.setContentText("Your account will be logout");
        Optional<ButtonType> input = newalert.showAndWait();
        if(input.get() == ButtonType.OK) {
            MainController.flightReservationSystem.customers.logoutallcustomer();
            HelloApplication.window.setScene(HelloApplication.MainMenu);
            HelloApplication.window.show();
        }
    }

    @FXML
    void LogoutAccounts(ActionEvent event) {
        MainController.flightReservationSystem.customers.logoutallcustomer();
        Alert message = new Alert(Alert.AlertType.INFORMATION);
        message.setTitle("Login");
        message.setContentText("Account Successfully logged out");
        message.showAndWait();
        HelloApplication.window.setScene(HelloApplication.MainMenu);
        HelloApplication.window.show();
    }
}
