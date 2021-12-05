package GUIcode.CustomerGUI;

import BusinessLogic.Reservation;
import BusinessLogic.Reservation;
import GUIcode.HelloApplication;
import GUIcode.MainController;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CancelReservation implements Initializable {

    @FXML
    private TableView<Reservation> Table;

    @FXML
    private TableColumn<Reservation, Integer> bookingreference;

    @FXML
    private TableColumn<Reservation, String> customername;

    @FXML
    private TableColumn<Reservation, String> flightid;

    @FXML
    private TableColumn<Reservation, String> cardholdername;

    @FXML
    private TableColumn<Reservation, String> cardnumber;

    @FXML
    private TableColumn<Reservation, Integer> cvv;

    @FXML
    private TableColumn<Reservation, String> expirydate;

    @FXML
    private TableColumn<Reservation, Integer> fares;

    @FXML
    private TableColumn<Reservation, Integer> passengers;

    @FXML
    private TableColumn<Reservation, String> type;

    public static Integer ref;

    ObservableList<Reservation> reservationlist = FXCollections.observableArrayList();

    private void addbutton()
    {
        TableColumn<Reservation,Void> colbtn = new TableColumn("Action");
        Callback<TableColumn<Reservation,Void>, TableCell<Reservation,Void>> cellfactory = new Callback<TableColumn<Reservation, Void>, TableCell<Reservation, Void>>() {
            @Override
            public TableCell<Reservation, Void> call(TableColumn<Reservation, Void> flightVoidTableColumn) {
                final TableCell<Reservation,Void> cell = new TableCell<Reservation,Void>()
                {
                    private final Button btn = new Button("Cancel");
                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Integer ref = getTableView().getItems().get(getIndex()).getBookingreference();
                            String Name = getTableView().getItems().get(getIndex()).getCustomername();
                            String id = getTableView().getItems().get(getIndex()).getFlightid();
                            boolean flag = false;
                            try
                            {
                                MainController.flightReservationSystem.reservations.deletereservation(ref,Name);
                                MainController.flightReservationSystem.totalflights.cancelseats(id,Name);
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
                                setReservation();
                                setroutine();
                                Alert message = new Alert(Alert.AlertType.INFORMATION);
                                message.setTitle("Reservation Deleted");
                                message.setContentText("Reservation Deleted Succesfully");
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

    public void setReservation()
    {
        reservationlist = FXCollections.observableArrayList();
        ArrayList<Reservation> reservationarray = MainController.flightReservationSystem.GetReservations();
        for (int i=0;i<reservationarray.size();i++)
        {
            reservationlist.add(reservationarray.get(i));
        }
    }

    public void setroutine()
    {
        bookingreference.setCellValueFactory(new PropertyValueFactory<Reservation,Integer>("bookingreference"));
        flightid.setCellValueFactory(new PropertyValueFactory<Reservation,String>("flightid"));
        customername.setCellValueFactory(new PropertyValueFactory<Reservation,String>("customername"));
        cardholdername.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Reservation, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Reservation, String> reservationStringCellDataFeatures) {
                return new SimpleStringProperty(reservationStringCellDataFeatures.getValue().getPayment().getCardholdername());
            }
        });

        cardnumber.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Reservation, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Reservation, String> reservationStringCellDataFeatures) {
                return new SimpleStringProperty(reservationStringCellDataFeatures.getValue().getPayment().getCardnumber());
            }
        });

        expirydate.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Reservation, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Reservation, String> reservationStringCellDataFeatures) {
                return new SimpleStringProperty(reservationStringCellDataFeatures.getValue().getPayment().getExpirydate());
            }
        });

        cvv.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Reservation, Integer>, ObservableValue<Integer>>() {
            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Reservation, Integer> reservationIntegerCellDataFeatures) {
                return new SimpleIntegerProperty(reservationIntegerCellDataFeatures.getValue().getPayment().getCvv()).asObject();
            }
        });

        passengers.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Reservation, Integer>, ObservableValue<Integer>>() {
            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Reservation, Integer> reservationIntegerCellDataFeatures) {
                return new SimpleIntegerProperty(reservationIntegerCellDataFeatures.getValue().getTicket().getNumberofpassengers()).asObject();
            }
        });

        fares.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Reservation, Integer>, ObservableValue<Integer>>() {
            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Reservation, Integer> reservationIntegerCellDataFeatures) {
                return new SimpleIntegerProperty(reservationIntegerCellDataFeatures.getValue().getTicket().getTotalfares()).asObject();
            }
        });
        type.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Reservation, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Reservation, String> reservationStringCellDataFeatures) {
                return new SimpleStringProperty(reservationStringCellDataFeatures.getValue().getTicket().getType());
            }
        });
        Table.setItems(reservationlist);
    }
    @FXML
    void BackToMenu(ActionEvent event) {
        HelloApplication.window.setScene(CustomerLoginScene.Customerfunctionsscene);
        HelloApplication.window.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        setReservation();
        addbutton();
        setroutine();
    }
}
