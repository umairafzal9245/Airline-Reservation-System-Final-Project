package GUIcode;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewAdminDetails implements Initializable {

    @FXML
    private Button Back;

    @FXML
    private TextField password;

    @FXML
    private TextField username;

    @FXML
    void BackToMenu(ActionEvent event) {
        HelloApplication.window.setScene(MainController.Adminsfunctionscene);
        HelloApplication.window.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        username.setText(MainController.flightReservationSystem.admin.getName());
        password.setText(Integer.toString(MainController.flightReservationSystem.admin.getLoginpin()));
    }
}