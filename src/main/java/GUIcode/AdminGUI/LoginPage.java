package GUIcode.AdminGUI;

import GUIcode.HelloApplication;
import GUIcode.MainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class LoginPage implements Initializable {

    static Scene Adminsfunctionscene;

    @FXML
    private PasswordField passwordfield;

    @FXML
    private TextField usernamefield;

    @FXML
    void LoginAdmin(ActionEvent event) throws IOException {
        if (passwordfield.getText().length() == 0)
            passwordfield.setStyle("-fx-border-color: red ; -fx-border-width: 2px;");
        else {
            passwordfield.setStyle("fx-border-width: 0px");
            String password = passwordfield.getText();
            try {
                MainController.flightReservationSystem.getAdmin().loginanaccount(Integer.parseInt(password));
            } catch (Exception e) {
                Alert message = new Alert(Alert.AlertType.ERROR);
                message.setTitle("Invalid Pin");
                message.setContentText("Enter the correct pin");
                message.showAndWait();
            }
            if (MainController.flightReservationSystem.getAdmin().isLogin()) {
                Alert message = new Alert(Alert.AlertType.INFORMATION);
                message.setTitle("Login Successfull");
                message.setContentText("You have successfully logged in!!!");
                message.showAndWait();
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("AdminFunctionsList.fxml"));
                Adminsfunctionscene = new Scene(fxmlLoader.load(), 500, 500);
                HelloApplication.window.setScene(Adminsfunctionscene);
                HelloApplication.window.show();
            }
        }
    }

    @FXML
    void BacktoMainMenu(ActionEvent event)
    {
        if(passwordfield.getText().length() > 0)
        {
            Alert newalert = new Alert(Alert.AlertType.CONFIRMATION);
            newalert.setTitle("Confirmation");
            newalert.setContentText("Are you sure");
            Optional<ButtonType> input = newalert.showAndWait();
            if(input.get() == ButtonType.OK)
            {
                HelloApplication.window.setScene(HelloApplication.MainMenu);
                HelloApplication.window.show();
            }
        }
        else
        {
            HelloApplication.window.setScene(HelloApplication.MainMenu);
            HelloApplication.window.show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        usernamefield.setText(MainController.flightReservationSystem.getAdmin().getName());
    }
}
