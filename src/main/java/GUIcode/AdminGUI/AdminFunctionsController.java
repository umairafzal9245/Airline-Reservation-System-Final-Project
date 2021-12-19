package GUIcode.AdminGUI;

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

public class AdminFunctionsController {

    private static Scene admindetailscene;
    private static Scene adminregisterscene;
    private static Scene viewallcustomersscene;
    private static Scene viewallresevationsscene;
    private static Scene manageflightsscene;

    @FXML
    void ExitMainMenu(ActionEvent event)
    {
        Alert newalert = new Alert(Alert.AlertType.CONFIRMATION);
        newalert.setTitle("Confirmation");
        newalert.setContentText("Your account will be logout");
        Optional<ButtonType> input = newalert.showAndWait();
        if(input.get() == ButtonType.OK)
        {
            MainController.getFlightReservationSystem().getAdmin().logout();
            HelloApplication.getWindow().setHeight(420);
            HelloApplication.getWindow().setWidth(500);
            HelloApplication.getWindow().setScene(SplashController.getMainmenuscene());
            HelloApplication.getWindow().show();
        }
    }

    @FXML
    void LogoutAdminAccount(ActionEvent event)
    {
            MainController.getFlightReservationSystem().getAdmin().logout();
            Alert message = new Alert(Alert.AlertType.INFORMATION);
            message.setTitle("Login");
            message.setContentText("Account Successfully logged out");
            message.showAndWait();
            HelloApplication.getWindow().setHeight(430);
            HelloApplication.getWindow().setWidth(500);
            HelloApplication.getWindow().setScene(SplashController.getMainmenuscene());
            HelloApplication.getWindow().show();
    }

    @FXML
    void ManageFlightSchedules(ActionEvent event) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("AdminResources/ManageFlights.fxml"));
            setManageflightsscene(new Scene(fxmlLoader.load(), 500, 400));
            HelloApplication.getWindow().setHeight(420);
            HelloApplication.getWindow().setWidth(510);
            HelloApplication.getWindow().setScene(getManageflightsscene());
            HelloApplication.getWindow().show();
    }

    @FXML
    void ModifyDetails(ActionEvent event) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("AdminResources/RegisterAdmin.fxml"));
        setAdminregisterscene(new Scene(fxmlLoader.load(), 400, 500));
        HelloApplication.getWindow().setHeight(540);
        HelloApplication.getWindow().setWidth(400);
        HelloApplication.getWindow().setScene(getAdminregisterscene());
        HelloApplication.getWindow().show();
    }

    @FXML
    void ViewAdminDetails(ActionEvent event) throws IOException
    {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("AdminResources/ViewAdminDetails.fxml"));
            setAdmindetailscene(new Scene(fxmlLoader.load(), 400, 500));
            HelloApplication.getWindow().setHeight(540);
            HelloApplication.getWindow().setWidth(400);
            HelloApplication.getWindow().setScene(getAdmindetailscene());
            HelloApplication.getWindow().show();
    }

    @FXML
    void ViewAllCustomers(ActionEvent event) throws IOException
    {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("AdminResources/ViewAllCustomersTable.fxml"));
            setViewallcustomersscene(new Scene(fxmlLoader.load(), 630, 500));
            HelloApplication.getWindow().setHeight(540);
            HelloApplication.getWindow().setWidth(640);
            HelloApplication.getWindow().setScene(getViewallcustomersscene());
            HelloApplication.getWindow().show();
    }

    @FXML
    void ViewAllReservations(ActionEvent event) throws IOException {

            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("AdminResources/ViewAllReservations.fxml"));
            setViewallresevationsscene(new Scene(fxmlLoader.load(), 840, 540));
            HelloApplication.getWindow().setHeight(540);
            HelloApplication.getWindow().setWidth(850);
            HelloApplication.getWindow().setScene(getViewallresevationsscene());
            HelloApplication.getWindow().show();
    }

    public static Scene getAdmindetailscene() {
        return admindetailscene;
    }

    public static void setAdmindetailscene(Scene admindetailscene) {
        AdminFunctionsController.admindetailscene = admindetailscene;
    }

    public static Scene getAdminregisterscene() {
        return adminregisterscene;
    }

    public static void setAdminregisterscene(Scene adminregisterscene) {
        AdminFunctionsController.adminregisterscene = adminregisterscene;
    }

    public static Scene getViewallcustomersscene() {
        return viewallcustomersscene;
    }

    public static void setViewallcustomersscene(Scene viewallcustomersscene) {
        AdminFunctionsController.viewallcustomersscene = viewallcustomersscene;
    }

    public static Scene getViewallresevationsscene() {
        return viewallresevationsscene;
    }

    public static void setViewallresevationsscene(Scene viewallresevationsscene) {
        AdminFunctionsController.viewallresevationsscene = viewallresevationsscene;
    }

    public static Scene getManageflightsscene() {
        return manageflightsscene;
    }

    public static void setManageflightsscene(Scene manageflightsscene) {
        AdminFunctionsController.manageflightsscene = manageflightsscene;
    }
}
