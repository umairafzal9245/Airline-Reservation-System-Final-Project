package GUIcode.CustomerGUI;

import GUIcode.HelloApplication;
import GUIcode.MainController;
import GUIcode.SplashController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.io.IOException;
import java.util.Optional;

public class CustomerFunctions {

    private static Scene displayaccountscene;
    private static Scene searchflightscene;
    private static Scene cancelreservationscene;
    private static Scene bookflight;
    private static Scene showreservations;
    private static Scene calculatefares;

    @FXML
    void PrintTicket(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("CustomerResources/ShowReservations.fxml"));
        setShowreservations(new Scene(fxmlLoader.load(), 850, 500));
        HelloApplication.getWindow().setHeight(540);
        HelloApplication.getWindow().setWidth(860);
        HelloApplication.getWindow().setScene(getShowreservations());
        HelloApplication.getWindow().show();
    }

    @FXML
    void BookaFlight(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("CustomerResources/BookFlight.fxml"));
        setBookflight(new Scene(fxmlLoader.load(), 500, 770));
        HelloApplication.getWindow().setHeight(540);
        HelloApplication.getWindow().setWidth(780);
        HelloApplication.getWindow().setScene(getBookflight());
        HelloApplication.getWindow().show();
    }

    @FXML
    void CalculateFares(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("CustomerResources/CalculateFares.fxml"));
        setCalculatefares(new Scene(fxmlLoader.load(), 730, 550));
        HelloApplication.getWindow().setHeight(590);
        HelloApplication.getWindow().setWidth(800);
        HelloApplication.getWindow().setScene(getCalculatefares());
        HelloApplication.getWindow().show();
    }

    @FXML
    void CancelReservation(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("CustomerResources/CancelReservation.fxml"));
        setCancelreservationscene(new Scene(fxmlLoader.load(), 840, 500));
        HelloApplication.getWindow().setHeight(540);
        HelloApplication.getWindow().setWidth(850);
        HelloApplication.getWindow().setScene(getCancelreservationscene());
        HelloApplication.getWindow().show();
    }

    @FXML
    void SearchFlight(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("CustomerResources/SearchFlight.fxml"));
        setSearchflightscene(new Scene(fxmlLoader.load(), 730, 500));
        HelloApplication.getWindow().setHeight(540);
        HelloApplication.getWindow().setWidth(730);
        HelloApplication.getWindow().setScene(getSearchflightscene());
        HelloApplication.getWindow().show();
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
                MainController.getFlightReservationSystem().getCustomers().DeleteCustomer();
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
                message.setContentText("Your account has been deleted");
                message.show();
                HelloApplication.getWindow().setHeight(430);
                HelloApplication.getWindow().setWidth(500);
                HelloApplication.getWindow().setScene(SplashController.getMainmenuscene());
                HelloApplication.getWindow().show();
            }
        }
    }

    @FXML
    void DisplayAccountDetails(ActionEvent event) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("CustomerResources/Displayaccount.fxml"));
        setDisplayaccountscene(new Scene(fxmlLoader.load(), 500, 600));
        HelloApplication.getWindow().setHeight(640);
        HelloApplication.getWindow().setWidth(500);
        HelloApplication.getWindow().setScene(getDisplayaccountscene());
        HelloApplication.getWindow().show();
    }

    @FXML
    void Exit(ActionEvent event) {
        Alert newalert = new Alert(Alert.AlertType.CONFIRMATION);
        newalert.setTitle("Confirmation");
        newalert.setContentText("Your account will be logout");
        Optional<ButtonType> input = newalert.showAndWait();
        if(input.get() == ButtonType.OK) {
            MainController.getFlightReservationSystem().getCustomers().logoutallcustomer();
            HelloApplication.getWindow().setHeight(430);
            HelloApplication.getWindow().setWidth(500);
            HelloApplication.getWindow().setScene(SplashController.getMainmenuscene());
            HelloApplication.getWindow().show();
        }
    }

    @FXML
    void LogoutAccounts(ActionEvent event) {
        MainController.getFlightReservationSystem().getCustomers().logoutallcustomer();
        Alert message = new Alert(Alert.AlertType.INFORMATION);
        message.setTitle("Login");
        message.setContentText("Account Successfully logged out");
        message.showAndWait();
        HelloApplication.getWindow().setHeight(430);
        HelloApplication.getWindow().setWidth(500);
        HelloApplication.getWindow().setScene(SplashController.getMainmenuscene());
        HelloApplication.getWindow().show();
    }

    public static Scene getDisplayaccountscene() {
        return displayaccountscene;
    }

    public static void setDisplayaccountscene(Scene displayaccountscene) {
        CustomerFunctions.displayaccountscene = displayaccountscene;
    }

    public static Scene getSearchflightscene() {
        return searchflightscene;
    }

    public static void setSearchflightscene(Scene searchflightscene) {
        CustomerFunctions.searchflightscene = searchflightscene;
    }

    public static Scene getCancelreservationscene() {
        return cancelreservationscene;
    }

    public static void setCancelreservationscene(Scene cancelreservationscene) {
        CustomerFunctions.cancelreservationscene = cancelreservationscene;
    }

    public static Scene getBookflight() {
        return bookflight;
    }

    public static void setBookflight(Scene bookflight) {
        CustomerFunctions.bookflight = bookflight;
    }

    public static Scene getShowreservations() {
        return showreservations;
    }

    public static void setShowreservations(Scene showreservations) {
        CustomerFunctions.showreservations = showreservations;
    }
    public static Scene getCalculatefares() {
        return calculatefares;
    }

    public static void setCalculatefares(Scene calculatefares) {
        CustomerFunctions.calculatefares = calculatefares;
    }
}
