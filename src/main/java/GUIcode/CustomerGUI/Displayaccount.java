package GUIcode.CustomerGUI;

import BusinessLogic.Customer;
import GUIcode.HelloApplication;
import GUIcode.MainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class Displayaccount implements Initializable {

    @FXML
    private TextField address;

    @FXML
    private TextField age;

    @FXML
    private TextField gender;

    @FXML
    private TextField passportnumber;

    @FXML
    private TextField pin;

    @FXML
    private TextField username;

    @FXML
    private TextField balance;

    @FXML
    void BacktoMainMenu(ActionEvent event) {
        HelloApplication.getWindow().setWidth(700);
        HelloApplication.getWindow().setHeight(540);
        HelloApplication.getWindow().setScene(CustomerLoginScene.getCustomerfunctionsscene());
        HelloApplication.getWindow().show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Customer custom = MainController.getFlightReservationSystem().getCustomers().getLoggedInCustomer();
        username.setText(custom.getName());
        pin.setText(Integer.toString(custom.getLoginpin()));
        address.setText(custom.getAddress());
        passportnumber.setText(String.valueOf(custom.getPassport_number()));
        gender.setText(custom.getGender());
        age.setText(Integer.toString(custom.getAge()));
        double bal = custom.getBalance();
        String format = String.format("%.2f$",bal);
        balance.setText(format);
    }
}
