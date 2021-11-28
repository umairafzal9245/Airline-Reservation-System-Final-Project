package GUIcode;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Optional;

public class RegisterAdmin {

    @FXML
    private Button backbutton;

    @FXML
    private PasswordField password;

    @FXML
    private Button submitbutton;

    @FXML
    private TextField username;

    @FXML
    void BacktoMainMenu(ActionEvent event) {
        if(username.getText().length() > 0 || password.getText().length() > 0)
        {
            Alert newalert = new Alert(Alert.AlertType.CONFIRMATION);
            newalert.setTitle("Confirmation");
            newalert.setContentText("Are you sure");
            Optional<ButtonType> input = newalert.showAndWait();
            if(input.get() == ButtonType.OK)
            {
                HelloApplication.window.setScene(MainController.Adminsfunctionscene);
                HelloApplication.window.show();
            }
        }
        else
        {
            HelloApplication.window.setScene(MainController.Adminsfunctionscene);
            HelloApplication.window.show();
        }
    }

    @FXML
    void Changedetails(ActionEvent event) {

        if(username.getText().length() == 0 || password.getText().length() == 0)
        {
            if (username.getText().length() == 0)
            username.setStyle("-fx-border-color: red ; -fx-border-width: 2px;");
            else
                username.setStyle("fx-border-width: 0px");

            if (password.getText().length() == 0)
                password.setStyle("-fx-border-color: red ; -fx-border-width: 2px;");
            else
                password.setStyle("fx-border-width: 0px");
        }
        else {
            boolean change = false;
            try {
                MainController.flightReservationSystem.admin.registeraccount(username.getText(), Integer.parseInt(password.getText()));
                    change = true;
            } catch (NumberFormatException e) {
                Alert message = new Alert(Alert.AlertType.ERROR);
                message.setTitle("Invalid Pin");
                message.setContentText("Enter the correct pin in digits");
                message.showAndWait();
            }
            if (change)
            {
                Alert message = new Alert(Alert.AlertType.INFORMATION);
                message.setTitle("Details Changed");
                message.setContentText("Details Successfully changed");
                message.showAndWait();
                HelloApplication.window.setScene(MainController.Adminsfunctionscene);
                HelloApplication.window.show();
            }
        }
    }
}
