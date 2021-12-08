package GUIcode.AdminGUI;

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

public class AdminFunctionsController {

    static Scene admindetailscene;
    static Scene adminregisterscene;
    static Scene viewallcustomersscene;
    static Scene viewallresevationsscene;
    static Scene manageflightsscene;

    @FXML
    void ExitMainMenu(ActionEvent event)
    {
        Alert newalert = new Alert(Alert.AlertType.CONFIRMATION);
        newalert.setTitle("Confirmation");
        newalert.setContentText("Your account will be logout");
        Optional<ButtonType> input = newalert.showAndWait();
        if(input.get() == ButtonType.OK)
        {
            MainController.flightReservationSystem.getAdmin().logout();
            HelloApplication.window.setScene(HelloApplication.MainMenu);
            HelloApplication.window.show();
        }
    }

    @FXML
    void LogoutAdminAccount(ActionEvent event)
    {
            MainController.flightReservationSystem.getAdmin().logout();
            Alert message = new Alert(Alert.AlertType.INFORMATION);
            message.setTitle("Login");
            message.setContentText("Account Successfully logged out");
            message.showAndWait();
            HelloApplication.window.setScene(HelloApplication.MainMenu);
            HelloApplication.window.show();
    }

    @FXML
    void ManageFlightSchedules(ActionEvent event) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ManageFlights.fxml"));
            manageflightsscene = new Scene(fxmlLoader.load(), 500, 500);
            HelloApplication.window.setScene(manageflightsscene);
            HelloApplication.window.show();
    }

    @FXML
    void ModifyDetails(ActionEvent event) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("RegisterAdmin.fxml"));
        adminregisterscene = new Scene(fxmlLoader.load(), 500, 500);
        HelloApplication.window.setScene(adminregisterscene);
        HelloApplication.window.show();
    }

    @FXML
    void ViewAdminDetails(ActionEvent event) throws IOException
    {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ViewAdminDetails.fxml"));
            admindetailscene = new Scene(fxmlLoader.load(), 500, 500);
            HelloApplication.window.setScene(admindetailscene);
            HelloApplication.window.show();
    }

    @FXML
    void ViewAllCustomers(ActionEvent event) throws IOException
    {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ViewAllCustomersTable.fxml"));
            viewallcustomersscene = new Scene(fxmlLoader.load(), 500, 500);
            HelloApplication.window.setScene(viewallcustomersscene);
            HelloApplication.window.show();
    }

    @FXML
    void ViewAllReservations(ActionEvent event) throws IOException {

            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ViewAllReservations.fxml"));
            viewallresevationsscene = new Scene(fxmlLoader.load(), 500, 500);
            HelloApplication.window.setScene(viewallresevationsscene);
            HelloApplication.window.show();
    }


}
