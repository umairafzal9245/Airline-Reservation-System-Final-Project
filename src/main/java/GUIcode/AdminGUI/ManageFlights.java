package GUIcode.AdminGUI;

import GUIcode.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

public class ManageFlights {

    private static Scene ShowAllFlightscene;
    private static Scene DeleteAllFlightscene;
    private static Scene AddNewFlightscene;

    @FXML
    void AddNewFlight(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("AddNewFlight.fxml"));
        setAddNewFlightscene(new Scene(fxmlLoader.load(), 500, 500));
        HelloApplication.getWindow().setScene(getAddNewFlightscene());
        HelloApplication.getWindow().show();
    }

    @FXML
    void BackToMenu(ActionEvent event) {
        HelloApplication.getWindow().setScene(LoginPage.getAdminsfunctionscene());
        HelloApplication.getWindow().show();
    }

    @FXML
    void DeleteFlight(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("DeleteFlight.fxml"));
        setDeleteAllFlightscene(new Scene(fxmlLoader.load(), 500, 500));
        HelloApplication.getWindow().setScene(getDeleteAllFlightscene());
        HelloApplication.getWindow().show();
    }

    @FXML
    void ShowAllFlights(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ShowAllFlights.fxml"));
        setShowAllFlightscene(new Scene(fxmlLoader.load(), 500, 500));
        HelloApplication.getWindow().setScene(getShowAllFlightscene());
        HelloApplication.getWindow().show();
    }
    public static Scene getShowAllFlightscene() {
        return ShowAllFlightscene;
    }

    public static void setShowAllFlightscene(Scene showAllFlightscene) {
        ShowAllFlightscene = showAllFlightscene;
    }

    public static Scene getDeleteAllFlightscene() {
        return DeleteAllFlightscene;
    }

    public static void setDeleteAllFlightscene(Scene deleteAllFlightscene) {
        DeleteAllFlightscene = deleteAllFlightscene;
    }

    public static Scene getAddNewFlightscene() {
        return AddNewFlightscene;
    }

    public static void setAddNewFlightscene(Scene addNewFlightscene) {
        AddNewFlightscene = addNewFlightscene;
    }
}
