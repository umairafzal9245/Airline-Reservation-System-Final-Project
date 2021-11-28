package GUIcode;

import BusinessLogic.PinUnverifiedException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginPage implements Initializable {

    @FXML
    private Button backfromadminlogin;

    @FXML
    private Button loginbutton;

    @FXML
    private PasswordField passwordfield;

    @FXML
    private TextField usernamefield;

    @FXML
    void LoginAdmin(ActionEvent event)
    {
        String password = passwordfield.getText();
        try {
            MainController.flightReservationSystem.admin.loginanaccount(Integer.parseInt(password));
        }
        catch (PinUnverifiedException | NumberFormatException e)
        {
            Alert message = new Alert(Alert.AlertType.ERROR);
            message.setTitle("Invalid Pin");
            message.setContentText("Enter the correct pin");
            message.showAndWait();
        }
        if (MainController.flightReservationSystem.admin.isLogin())
        {
            Alert message = new Alert(Alert.AlertType.INFORMATION);
            message.setTitle("Login Successfull");
            message.setContentText("You have successfully logged in!!!");
            message.showAndWait();
            HelloApplication.window.setScene(MainController.Adminsfunctionscene);
            HelloApplication.window.show();
        }
    }

    @FXML
    void ShowAdminFunctionsList(ActionEvent event) {
        HelloApplication.window.setScene(MainController.Adminsfunctionscene);
        HelloApplication.window.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        usernamefield.setText(MainController.flightReservationSystem.admin.getName());
    }
}
