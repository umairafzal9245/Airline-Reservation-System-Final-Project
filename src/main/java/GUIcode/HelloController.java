package GUIcode;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class HelloController {

    @FXML
    private Button MainAdminButton;

    @FXML
    private Button MainCustomerbutton;

    @FXML
    private Label welcomeText;

    @FXML
    void InvokeAdminFunctions(ActionEvent event) {
        System.out.println("Admin clicked");
    }

    @FXML
    void InvokeCustomerFunctions(ActionEvent event) {
        System.out.println("Customer clicked");
    }

}
