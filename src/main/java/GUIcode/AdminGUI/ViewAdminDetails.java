package GUIcode.AdminGUI;

import GUIcode.HelloApplication;
import GUIcode.MainController;
import javafx.event.ActionEvent;
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

    @FXML
    void BackToMenu(ActionEvent event) {
        HelloApplication.getWindow().setWidth(700);
        HelloApplication.getWindow().setHeight(540);
        HelloApplication.getWindow().setScene(LoginPage.getAdminsfunctionscene());
        HelloApplication.getWindow().show();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        username.setText(MainController.getFlightReservationSystem().getAdmin().getName());
        password.setText(Integer.toString(MainController.getFlightReservationSystem().getAdmin().getLoginpin()));
    }
}