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
    void BacktoMainMenu(ActionEvent event) {
        HelloApplication.window.setScene(CustomerLoginScene.Customerfunctionsscene);
        HelloApplication.window.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Customer custom = MainController.flightReservationSystem.getCustomers().getLoggedInCustomer();
        username.setText(custom.getName());
        pin.setText(Integer.toString(custom.getLoginpin()));
        address.setText(custom.getAddress());
        passportnumber.setText(String.valueOf(custom.getPassport_number()));
        gender.setText(custom.getGender());
        age.setText(Integer.toString(custom.getAge()));
    }
}
