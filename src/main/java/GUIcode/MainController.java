package GUIcode;

import BusinessLogic.FlightReservationSystem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;


public class MainController implements Initializable {

    private static Scene loginscene;
    private static Scene customerloginscene;

    private static ArrayList<String> countries;
    private static final FlightReservationSystem flightReservationSystem = new FlightReservationSystem();

    @FXML
    void InvokeAdminFunctions(ActionEvent event) throws IOException {
        if(MainController.getFlightReservationSystem().getAdmin().isLogin() == false)
        {
            HelloApplication.getWindow().setWidth(700);
            HelloApplication.getWindow().setHeight(540);
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("AdminResources/LoginPage.fxml"));
            setLoginscene(new Scene(fxmlLoader.load(), 700, 500));
            HelloApplication.getWindow().setScene(getLoginscene());
            HelloApplication.getWindow().show();
        }
    }

    @FXML
    void InvokeCustomerFunctions(ActionEvent event) throws IOException {
        HelloApplication.getWindow().setWidth(700);
        HelloApplication.getWindow().setHeight(540);
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("CustomerResources/CustomerLogin.fxml"));
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
    void Loadcountries()
    {
        countries = new ArrayList<>();
        try {
            File f = new File("Airports.txt");
            BufferedReader b = new BufferedReader(new FileReader(f));
            String readLine = "";
            while ((readLine = b.readLine()) != null) {
                countries.add(readLine);
            }
        }
        catch (Exception e)
        {
            System.out.println("Error loading countries");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Loadcountries();
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
    public static ArrayList<String> getCountries() {
        return countries;
    }

    public static void setCountries(ArrayList<String> countries) {
        MainController.countries = countries;
    }
}
