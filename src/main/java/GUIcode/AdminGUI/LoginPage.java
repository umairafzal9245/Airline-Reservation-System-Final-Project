package GUIcode.AdminGUI;

import GUIcode.HelloApplication;
import GUIcode.MainController;
import GUIcode.SplashController;
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

    private static Scene Adminsfunctionscene;

    @FXML
    private PasswordField passwordfield;

    @FXML
    private TextField usernamefield;

    @FXML
    void LoginAdmin(ActionEvent event) throws IOException {
        if (passwordfield.getText().length() != 4)
            passwordfield.setStyle("-fx-background-color: transparent; -fx-border-color: red ; -fx-border-width: 0px 0px 2px 0px;");
        else {
            passwordfield.setStyle("-fx-background-color: transparent; -fx-border-color: #0598ff; -fx-border-width: 0px 0px 2px 0px;");
            String password = passwordfield.getText();
            try {
                MainController.getFlightReservationSystem().getAdmin().loginanaccount(Integer.parseInt(password));
            } catch (Exception e) {
                Alert message = new Alert(Alert.AlertType.ERROR);
                message.setTitle("Invalid Pin");
                message.setContentText("Enter the correct pin");
                message.showAndWait();
            }
            if (MainController.getFlightReservationSystem().getAdmin().isLogin()) {
                Alert message = new Alert(Alert.AlertType.INFORMATION);
                message.setTitle("Login Successfull");
                message.setContentText("You have successfully logged in!!!");
                message.showAndWait();
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("AdminResources/AdminFunctionsList.fxml"));
                setAdminsfunctionscene(new Scene(fxmlLoader.load(), 650, 500));
                HelloApplication.getWindow().setWidth(650);
                HelloApplication.getWindow().setHeight(520);
                HelloApplication.getWindow().setScene(getAdminsfunctionscene());
                HelloApplication.getWindow().show();
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
                HelloApplication.getWindow().setHeight(430);
                HelloApplication.getWindow().setWidth(500);
                HelloApplication.getWindow().setScene(SplashController.getMainmenuscene());
                HelloApplication.getWindow().show();
            }
        }
        else
        {
            HelloApplication.getWindow().setHeight(430);
            HelloApplication.getWindow().setWidth(500);
            HelloApplication.getWindow().setScene(SplashController.getMainmenuscene());
            HelloApplication.getWindow().show();
        }
    }
    public static Scene getAdminsfunctionscene() {
        return Adminsfunctionscene;
    }

    public static void setAdminsfunctionscene(Scene adminsfunctionscene) {
        Adminsfunctionscene = adminsfunctionscene;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        usernamefield.setText(MainController.getFlightReservationSystem().getAdmin().getName());
    }
}
