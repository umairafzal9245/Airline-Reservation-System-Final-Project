package GUIcode.AdminGUI;

import GUIcode.HelloApplication;
import GUIcode.MainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Optional;

public class RegisterAdmin {

    @FXML
    private PasswordField password;

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
                HelloApplication.getWindow().setWidth(650);
                HelloApplication.getWindow().setHeight(520);
                HelloApplication.getWindow().setScene(LoginPage.getAdminsfunctionscene());
                HelloApplication.getWindow().show();
            }
        }
        else
        {
            HelloApplication.getWindow().setWidth(650);
            HelloApplication.getWindow().setHeight(520);
            HelloApplication.getWindow().setScene(LoginPage.getAdminsfunctionscene());
            HelloApplication.getWindow().show();
        }
    }

    @FXML
    void Changedetails(ActionEvent event) {

        if(username.getText().length() == 0 || password.getText().length() != 4)
        {
            if (username.getText().length() == 0)
            username.setStyle("-fx-background-color: transparent; -fx-border-color: red ; -fx-border-width: 0px 0px 2px 0px;");
            else
                username.setStyle("-fx-background-color: transparent; -fx-border-color: #0598ff; -fx-border-width: 0px 0px 2px 0px;");

            if (password.getText().length() != 4)
                password.setStyle("-fx-background-color: transparent; -fx-border-color: red ; -fx-border-width: 0px 0px 2px 0px;");
            else
                password.setStyle("-fx-background-color: transparent; -fx-border-color: #0598ff; -fx-border-width: 0px 0px 2px 0px;");
        }
        else {
            boolean change = false;
            try {
                MainController.getFlightReservationSystem().getAdmin().registeraccount(username.getText(), Integer.parseInt(password.getText()));
                    change = true;
            } catch (Exception e) {
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
                HelloApplication.getWindow().setWidth(650);
                HelloApplication.getWindow().setHeight(520);
                HelloApplication.getWindow().setScene(LoginPage.getAdminsfunctionscene());
                HelloApplication.getWindow().show();
            }
        }
    }
}
