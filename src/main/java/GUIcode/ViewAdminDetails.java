package GUIcode;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewAdminDetails implements Initializable {

    @FXML
    private TextField password;

    @FXML
    private TextField username;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        username.setText(MainController.flightReservationSystem.admin.getName());
        password.setText(Integer.toString(MainController.flightReservationSystem.admin.getLoginpin()));
    }
}