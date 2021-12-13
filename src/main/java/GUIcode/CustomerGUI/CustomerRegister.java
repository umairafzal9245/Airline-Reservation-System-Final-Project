package GUIcode.CustomerGUI;

import GUIcode.HelloApplication;
import GUIcode.MainController;
import GUIcode.SplashController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;

public class CustomerRegister implements Initializable {

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    @FXML
    private TextField address;

    @FXML
    private TextField age;

    @FXML
    private TextField passportnumber;

    @FXML
    private ChoiceBox<String> gender;

    @FXML
    void BacktoMainMenu(ActionEvent event) {
        Alert newalert = new Alert(Alert.AlertType.CONFIRMATION);
        newalert.setTitle("Confirmation");
        newalert.setContentText("Are you sure");
        Optional<ButtonType> input = newalert.showAndWait();
        if(input.get() == ButtonType.OK)
        {
            HelloApplication.getWindow().setHeight(440);
            HelloApplication.getWindow().setWidth(500);
            HelloApplication.getWindow().setScene(SplashController.getMainmenuscene());
            HelloApplication.getWindow().show();
        }
    }

    @FXML
    void RegisterUser(ActionEvent event) throws IOException {
        if(username.getText().length() == 0)
        {
            username.setStyle("-fx-border-color: red ; -fx-border-width: 2px;");
        }
        else
        {
            username.setStyle("fx-border-width: 0px");
            String userna = username.getText();
            if(password.getText().length() == 0 || !isNumeric(password.getText()))
            {
                password.setStyle("-fx-border-color: red ; -fx-border-width: 2px;");
            }
            else
            {
                password.setStyle("fx-border-width: 0px");
                String pass = password.getText();
                if(passportnumber.getText().length() == 0 || !isNumeric(passportnumber.getText()))
                {
                    passportnumber.setStyle("-fx-border-color: red ; -fx-border-width: 2px;");
                }
                else
                {
                    passportnumber.setStyle("fx-border-width: 0px");
                    String passport = passportnumber.getText();
                    if(age.getText().length() == 0 || !isNumeric(age.getText()))
                    {
                        age.setStyle("-fx-border-color: red ; -fx-border-width: 2px;");
                    }
                    else
                    {
                        if(Integer.parseInt(age.getText()) < 18 || Integer.parseInt(age.getText()) > 100)
                        {
                            age.setStyle("-fx-border-color: red ; -fx-border-width: 2px;");
                            age.setText("must be over 18");
                        }
                        else
                        {
                            age.setStyle("fx-border-width: 0px");
                            String ag = age.getText();
                            if(address.getText().length() == 0)
                            {
                                address.setStyle("-fx-border-color: red ; -fx-border-width: 2px;");
                            }
                            else
                            {
                                address.setStyle("fx-border-width: 0px");
                                String add = address.getText();
                                String gen = gender.getValue();
                                boolean flag = false;
                                try {
                                    Random r = new Random();
                                    Double bal = 1000 * r.nextDouble();
                                    MainController.getFlightReservationSystem().getCustomers().RegisteranAccount(userna, gen, Integer.parseInt(ag), add, Integer.parseInt(passport), Integer.parseInt(pass),true,bal);
                                    flag = true;
                                }
                                catch (Exception e) {
                                    Alert message = new Alert(Alert.AlertType.ERROR);
                                    message.setTitle("Error registering");
                                    message.setContentText(e.getMessage());
                                    message.showAndWait();
                                }
                                if(flag)
                                {
                                    Alert message = new Alert(Alert.AlertType.INFORMATION);
                                    message.setTitle("Registration Added");
                                    message.setContentText("Customer Successfully added");
                                    message.showAndWait();
                                    FXMLLoader fxmlLoader2 = new FXMLLoader(HelloApplication.class.getResource("CustomerResources/CustomerFunctionsList.fxml"));
                                    CustomerLoginScene.setCustomerfunctionsscene(new Scene(fxmlLoader2.load(), 500, 500));
                                    HelloApplication.getWindow().setScene(CustomerLoginScene.getCustomerfunctionsscene());
                                    HelloApplication.getWindow().show();
                                }
                            }
                        }
                    }
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> gen = FXCollections.observableArrayList("Male","Female","Other");
        gender.setItems(gen);
        gender.setValue("Male");
    }
}
