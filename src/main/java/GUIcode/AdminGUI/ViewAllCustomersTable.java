package GUIcode.AdminGUI;

import BusinessLogic.Customer;
import BusinessLogic.Customer;
import GUIcode.HelloApplication;
import GUIcode.MainController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.net.URL;
import java.util.*;

public class ViewAllCustomersTable implements Initializable {

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

    @FXML
    private TableColumn<Customer, Double> balance;

    ObservableList<Customer> customerslist = FXCollections.observableArrayList();
    @FXML
    void BackToMenu(ActionEvent event) {
        HelloApplication.getWindow().setScene(LoginPage.getAdminsfunctionscene());
        HelloApplication.getWindow().show();
    }

    private void addbutton()
    {
        TableColumn<Customer,Void> colbtn = new TableColumn("Action");
        Callback<TableColumn<Customer,Void>, TableCell<Customer,Void>> cellfactory = new Callback<TableColumn<Customer, Void>, TableCell<Customer, Void>>() {
            @Override
            public TableCell<Customer, Void> call(TableColumn<Customer, Void> flightVoidTableColumn) {
                final TableCell<Customer,Void> cell = new TableCell<Customer,Void>()
                {
                    private final Button btn = new Button("Delete");
                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Integer pass = getTableView().getItems().get(getIndex()).getPassport_number();
                            boolean flag = false;
                            try
                            {
                                MainController.getFlightReservationSystem().getCustomers().DeleteAccount(pass);
                                flag = true;
                            }
                            catch (Exception e)
                            {
                                Alert message = new Alert(Alert.AlertType.ERROR);
                                message.setTitle("Invalid");
                                message.setContentText(e.getMessage());
                                message.showAndWait();
                            }
                            if(flag)
                            {
                                setcustomer();
                                setroutine();
                                Alert message = new Alert(Alert.AlertType.INFORMATION);
                                message.setTitle("Customer Deleted");
                                message.setContentText("Customer Deleted Succesfully");
                                message.showAndWait();
                            }
                        });
                    }
                    @Override
                    public void updateItem(Void item,boolean empty)
                    {
                        super.updateItem(item,empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };
        colbtn.setCellFactory(cellfactory);
        Table.getColumns().add(colbtn);
    }

    public void setroutine()
    {
        name.setCellValueFactory(new PropertyValueFactory<Customer,String>("name"));
        age.setCellValueFactory(new PropertyValueFactory<Customer,Integer>("age"));
        gender.setCellValueFactory(new PropertyValueFactory<Customer,String>("gender"));
        address.setCellValueFactory(new PropertyValueFactory<Customer,String>("address"));
        passport.setCellValueFactory(new PropertyValueFactory<Customer,String>("passport_number"));
        loginpin.setCellValueFactory(new PropertyValueFactory<Customer,Integer>("loginpin"));
        balance.setCellValueFactory(new PropertyValueFactory<Customer,Double>("balance"));

        Table.setItems(customerslist);
    }
    public void setcustomer()
    {
        customerslist = FXCollections.observableArrayList();
        ArrayList<Customer> customerarray = new ArrayList<>();
        customerarray = MainController.getFlightReservationSystem().getCustomers().getCustomerslist();
        customerslist.addAll(customerarray);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        setcustomer();
        setroutine();
        addbutton();
    }
}
