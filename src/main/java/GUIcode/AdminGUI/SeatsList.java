package GUIcode.AdminGUI;

import BusinessLogic.FlightIDIncorrectException;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SeatsList implements Initializable {

    @FXML
    private TableColumn<Seats, String> bookingstatus;

    @FXML
    private TableColumn<Seats, String> customername;

    @FXML
    private TableColumn<Seats, String> flightid;

    @FXML
    private TableColumn<Seats, Integer> seatnumber;

    @FXML
    private TableView<Seats> seatstable;

    ObservableList<Seats> seatslist = FXCollections.observableArrayList();

    @FXML
    void Exit(ActionEvent event) {
        HelloApplication.window.setScene(ManageFlights.ShowAllFlightscene);
        HelloApplication.window.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ArrayList<Seats> getseats = null;
        try {
            getseats = MainController.flightReservationSystem.totalflights.GetFlightSeats(ShowAllFlights.data);
        } catch (FlightIDIncorrectException e) {
            e.printStackTrace();
        }
        for (int i=0;i<getseats.size();i++)
        {
            seatslist.add(getseats.get(i));
        }

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
       customername.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Seats, String>, ObservableValue<String>>() {
           @Override
           public ObservableValue<String> call(TableColumn.CellDataFeatures<Seats, String> seatsStringCellDataFeatures) {
               return new SimpleStringProperty(seatsStringCellDataFeatures.getValue().getCustomername());
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
