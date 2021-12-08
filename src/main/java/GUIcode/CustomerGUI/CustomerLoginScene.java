package GUIcode.CustomerGUI;

import GUIcode.HelloApplication;
import GUIcode.MainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.Optional;

public class CustomerLoginScene {

    static Scene Customerfunctionsscene;
    static Scene CustomerRegisterscene;

    @FXML
    private PasswordField password;

    @FXML
    private TextField passportnumber;

    @FXML
    void RegisterUser(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("CustomerRegister.fxml"));
        CustomerRegisterscene = new Scene(fxmlLoader.load(), 500, 500);
        HelloApplication.window.setScene(CustomerRegisterscene);
        HelloApplication.window.show();
    }

    @FXML
    void LoginUser(ActionEvent event) throws IOException
    {
        if(passportnumber.getText() == null || passportnumber.getText().length() == 0 ||  !isNumeric(passportnumber.getText()))
        {
            passportnumber.setStyle("-fx-border-color: red ; -fx-border-width: 2px;");
        }
        else {
            passportnumber.setStyle("fx-border-width: 0px");
            String user = passportnumber.getText();
            if (passportnumber.getText() == null || password.getText().length() == 0 || !isNumeric(password.getText()))
                password.setStyle("-fx-border-color: red ; -fx-border-width: 2px;");
            else {
                password.setStyle("fx-border-width: 0px");
                String passwordd = password.getText();
                try {
                    MainController.flightReservationSystem.getCustomers().loginanaccount(Integer.valueOf(user), Integer.parseInt(passwordd));
                } catch (Exception e) {
                    Alert message = new Alert(Alert.AlertType.ERROR);
                    message.setTitle("Error");
                    message.setContentText(e.getMessage());
                    message.showAndWait();
                }
                if (MainController.flightReservationSystem.getCustomers().checkloginofcustomer()) {
                    Alert message = new Alert(Alert.AlertType.INFORMATION);
                    message.setTitle("Login Successfull");
                    message.setContentText("You have successfully logged in!!!");
                    message.showAndWait();
                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("CustomerFunctionsList.fxml"));
                    Customerfunctionsscene = new Scene(fxmlLoader.load(), 500, 500);
                    HelloApplication.window.setScene(Customerfunctionsscene);
                    HelloApplication.window.show();
                }
            }
        }
    }

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    @FXML
    void BacktoMainMenu(ActionEvent event)
    {
        if(password.getText().length() > 0)
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

}
