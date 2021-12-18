package GUIcode.CustomerGUI;

import GUIcode.HelloApplication;
import GUIcode.MainController;
import GUIcode.SplashController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.Optional;

public class CustomerLoginScene {

    private static Scene Customerfunctionsscene;
    private static Scene CustomerRegisterscene;

    @FXML
    private PasswordField password;

    @FXML
    private TextField passportnumber;

    @FXML
    void RegisterUser(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("CustomerResources/CustomerRegister.fxml"));
        setCustomerRegisterscene(new Scene(fxmlLoader.load(), 460, 550));
        HelloApplication.getWindow().setWidth(480);
        HelloApplication.getWindow().setHeight(590);
        HelloApplication.getWindow().setScene(getCustomerRegisterscene());
        HelloApplication.getWindow().show();
    }

    @FXML
    void LoginUser(ActionEvent event) throws IOException
    {
        if(passportnumber.getText() == null || passportnumber.getText().length() != 8 ||  !isNumeric(passportnumber.getText()))
        {
            passportnumber.setStyle("-fx-background-color: transparent; -fx-border-color: red ; -fx-border-width: 0px 0px 2px 0px;");
        }
        else {
            passportnumber.setStyle("-fx-background-color: transparent; -fx-border-color: #0598ff; -fx-border-width: 0px 0px 2px 0px;");
            String user = passportnumber.getText();
            if (password.getText() == null || password.getText().length() != 4 || !isNumeric(password.getText()))
                password.setStyle("-fx-background-color: transparent; -fx-border-color: red ; -fx-border-width: 0px 0px 2px 0px;");
            else {
                password.setStyle("-fx-background-color: transparent; -fx-border-color: #0598ff; -fx-border-width: 0px 0px 2px 0px;");
                String passwordd = password.getText();
                try {
                    MainController.getFlightReservationSystem().getCustomers().loginanaccount(Integer.valueOf(user), Integer.parseInt(passwordd));
                } catch (Exception e) {
                    Alert message = new Alert(Alert.AlertType.ERROR);
                    message.setTitle("Error");
                    message.setContentText(e.getMessage());
                    message.showAndWait();
                }
                if (MainController.getFlightReservationSystem().getCustomers().checkloginofcustomer()) {
                    Alert message = new Alert(Alert.AlertType.INFORMATION);
                    message.setTitle("Login Successfull");
                    message.setContentText("You have successfully logged in!!!");
                    message.showAndWait();
                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("CustomerResources/CustomerFunctionsList.fxml"));
                    setCustomerfunctionsscene(new Scene(fxmlLoader.load(), 650, 500));
                    HelloApplication.getWindow().setWidth(650);
                    HelloApplication.getWindow().setHeight(540);
                    HelloApplication.getWindow().setScene(getCustomerfunctionsscene());
                    HelloApplication.getWindow().show();
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
    public static Scene getCustomerfunctionsscene() {
        return Customerfunctionsscene;
    }

    public static void setCustomerfunctionsscene(Scene customerfunctionsscene) {
        Customerfunctionsscene = customerfunctionsscene;
    }

    public static Scene getCustomerRegisterscene() {
        return CustomerRegisterscene;
    }

    public static void setCustomerRegisterscene(Scene customerRegisterscene) {
        CustomerRegisterscene = customerRegisterscene;
    }
}
