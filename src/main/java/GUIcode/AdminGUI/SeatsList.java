package GUIcode.AdminGUI;

import BusinessLogic.Seats;
import GUIcode.HelloApplication;
import GUIcode.MainController;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SeatsList implements Initializable {

    @FXML
    private TableColumn<Seats, String> bookingstatus;

    @FXML
    private TableColumn<Seats, Integer> customerpassport;

    @FXML
    private TableColumn<Seats, String> flightid;

    @FXML
    private TableColumn<Seats, Integer> seatnumber;

    @FXML
    private TableView<Seats> seatstable;

    final ObservableList<Seats> seatslist = FXCollections.observableArrayList();

    @FXML
    void Exit(ActionEvent event) {
        HelloApplication.getWindow().setWidth(790);
        HelloApplication.getWindow().setHeight(660);
        HelloApplication.getWindow().setScene(ManageFlights.getShowAllFlightscene());
        HelloApplication.getWindow().show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ArrayList<Seats> getseats = null;
        try {
            getseats = MainController.getFlightReservationSystem().getTotalflights().GetFlightSeats(ShowAllFlights.getData());
        } catch (Exception e) {
            e.printStackTrace();
        }
        seatslist.addAll(getseats);

        flightid.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Seats, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Seats, String> seatsStringCellDataFeatures) {
                return new SimpleStringProperty(seatsStringCellDataFeatures.getValue().getFlightid());
            }
        });
       seatnumber.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Seats, Integer>, ObservableValue<Integer>>() {
           @Override
           public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Seats, Integer> seatsIntegerCellDataFeatures) {
               return new SimpleIntegerProperty(seatsIntegerCellDataFeatures.getValue().getNumber()).asObject();
           }
       });
       customerpassport.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Seats, Integer>, ObservableValue<Integer>>() {
           @Override
           public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Seats, Integer> seatsIntegerCellDataFeatures) {
               return new SimpleIntegerProperty(seatsIntegerCellDataFeatures.getValue().getCustomerpassport()).asObject();
           }
       });
        bookingstatus.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Seats, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Seats, String> seatsStringCellDataFeatures) {
                return new SimpleStringProperty(seatsStringCellDataFeatures.getValue().getStatus());
            }
        });
        seatstable.setItems(seatslist);
    }
}
