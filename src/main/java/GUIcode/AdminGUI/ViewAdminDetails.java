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
        HelloApplication.window.setScene(LoginPage.Adminsfunctionscene);
        HelloApplication.window.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        username.setText(MainController.flightReservationSystem.getAdmin().getName());
        password.setText(Integer.toString(MainController.flightReservationSystem.getAdmin().getLoginpin()));
    }
}