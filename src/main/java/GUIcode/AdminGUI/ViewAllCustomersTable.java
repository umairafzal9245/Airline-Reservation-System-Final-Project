package GUIcode.AdminGUI;

import BusinessLogic.Customer;
import GUIcode.HelloApplication;
import GUIcode.MainController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.*;

public class ViewAllCustomersTable implements Initializable {

    @FXML
    private Button Back;

    @FXML
    private TableView<Customer> Table;

    @FXML
    private TableColumn<Customer, String> address;

    @FXML
    private TableColumn<Customer, Integer> age;

    @FXML
    private TableColumn<Customer, String> gender;

    @FXML
    private TableColumn<Customer, Integer> loginpin;

    @FXML
    private TableColumn<Customer, String> name;

    @FXML
    private TableColumn<Customer, String> passport;

    final ObservableList<Customer> customerslist = FXCollections.observableArrayList();
    @FXML
    void BackToMenu(ActionEvent event) {
        HelloApplication.window.setScene(LoginPage.Adminsfunctionscene);
        HelloApplication.window.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        ArrayList<Customer> customerarray = MainController.flightReservationSystem.getCustomers().getCustomerslist();
        customerslist.addAll(customerarray);

        name.setCellValueFactory(new PropertyValueFactory<Customer,String>("name"));
        age.setCellValueFactory(new PropertyValueFactory<Customer,Integer>("age"));
        gender.setCellValueFactory(new PropertyValueFactory<Customer,String>("gender"));
        address.setCellValueFactory(new PropertyValueFactory<Customer,String>("address"));
        passport.setCellValueFactory(new PropertyValueFactory<Customer,String>("passport_number"));
        loginpin.setCellValueFactory(new PropertyValueFactory<Customer,Integer>("loginpin"));

        Table.setItems(customerslist);
    }
}
