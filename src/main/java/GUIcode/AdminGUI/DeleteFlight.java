package GUIcode.AdminGUI;

import BusinessLogic.Flight;
import BusinessLogic.OneWayFlight;
import BusinessLogic.TwoWayFlight;
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
import javafx.scene.control.*;
import javafx.util.Callback;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DeleteFlight implements Initializable {

    @FXML
    private TableView<Flight> Onewaytable;

    @FXML
    private TableColumn<Flight, String> onewayclass;

    @FXML
    private TableColumn<Flight, String> onewaydeparturedate;

    @FXML
    private TableColumn<Flight, String> onewaydeparturetime;

    @FXML
    private TableColumn<Flight, String> onewaydestination;

    @FXML
    private TableColumn<Flight, Integer> onewayfares;

    @FXML
    private TableColumn<Flight, String> onewayid;

    @FXML
    private TableColumn<Flight, String> onewayorigin;

    @FXML
    private TableColumn<Flight, Integer> onewaypassenger;

    @FXML
    private TableView<Flight> twowaytable;

    @FXML
    private TableColumn<Flight, String> twowayarrivaldate;

    @FXML
    private TableColumn<Flight, String> twowayarrivaltime;

    @FXML
    private TableColumn<Flight, String> twowayclass;

    @FXML
    private TableColumn<Flight, String> twowaydeparturedate;

    @FXML
    private TableColumn<Flight, String> twowaydeparturetime;

    @FXML
    private TableColumn<Flight, String> twowaydestination;

    @FXML
    private TableColumn<Flight, Integer> twowayfares;

    @FXML
    private TableColumn<Flight, String> twowayid;

    @FXML
    private TableColumn<Flight, String> twowayorigin;

    @FXML
    private TableColumn<Flight, Integer> twowaypassenger;

    @FXML
    private TextField flightidfield;

    ObservableList<Flight> oneWayFlights = FXCollections.observableArrayList();
    ObservableList<Flight> twoWayFlights = FXCollections.observableArrayList();

    @FXML
    void Backtomainmenu(ActionEvent event) {
        HelloApplication.getWindow().setHeight(420);
        HelloApplication.getWindow().setWidth(510);
        HelloApplication.getWindow().setScene(AdminFunctionsController.getManageflightsscene());
        HelloApplication.getWindow().show();
    }
    @FXML
    void DeleteFlight(ActionEvent event)
    {
        if(flightidfield.getText().length() == 0)
            flightidfield.setStyle("-fx-background-color: transparent; -fx-border-color: red; -fx-border-width: 0px 0px 2px 0px;");
            else {
            flightidfield.setStyle("-fx-background-color: transparent; -fx-border-color: #0598ff; -fx-border-width: 0px 0px 2px 0px;");
            String flightid = flightidfield.getText();
            boolean change = false;
            try {
                MainController.getFlightReservationSystem().getTotalflights().deleteflight(flightid);
                change = true;
            }
            catch (Exception e)
            {
                Alert message = new Alert(Alert.AlertType.ERROR);
                message.setTitle("Invalid FlightID");
                message.setContentText("Enter the correct flight id");
                message.showAndWait();
            }
            if (change)
            {
                setobservablelist();
                setonewaytableroutine();
                settwowaytableroutine();
                Alert message = new Alert(Alert.AlertType.INFORMATION);
                message.setTitle("Flight Deleted");
                message.setContentText("Flight Deleted Succesfully");
                message.showAndWait();
            }
        }
    }
    public void setonewaytableroutine()
    {
        onewayid.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Flight, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Flight, String> flightStringCellDataFeatures) {
                return new SimpleStringProperty(flightStringCellDataFeatures.getValue().getId());
            }
        });
        onewayorigin.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Flight, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Flight, String> flightStringCellDataFeatures) {
                return new SimpleStringProperty(flightStringCellDataFeatures.getValue().getOrigin());
            }
        });
        onewaydestination.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Flight, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Flight, String> flightStringCellDataFeatures) {
                return new SimpleStringProperty(flightStringCellDataFeatures.getValue().getDestination());
            }
        });
        onewayfares.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Flight, Integer>, ObservableValue<Integer>>() {
            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Flight, Integer> flightIntegerCellDataFeatures) {
                return new SimpleIntegerProperty(flightIntegerCellDataFeatures.getValue().getFares()).asObject();
            }
        });
        onewayclass.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Flight, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Flight, String> flightStringCellDataFeatures) {
                return new SimpleStringProperty(flightStringCellDataFeatures.getValue().getClasse());
            }
        });
        onewaydeparturedate.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Flight, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Flight, String> flightStringCellDataFeatures) {
                return new SimpleStringProperty(flightStringCellDataFeatures.getValue().getDeparture_date());
            }
        });
        onewaydeparturetime.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Flight, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Flight, String> flightStringCellDataFeatures) {
                return new SimpleStringProperty(flightStringCellDataFeatures.getValue().getDeparture_time());
            }
        });
        onewaypassenger.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Flight, Integer>, ObservableValue<Integer>>() {
            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Flight, Integer> flightIntegerCellDataFeatures) {
                return new SimpleIntegerProperty(flightIntegerCellDataFeatures.getValue().getCapacity()).asObject();
            }
        });
        Onewaytable.setItems(oneWayFlights);
    }
    public void settwowaytableroutine()
    {
        twowayid.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Flight, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Flight, String> flightStringCellDataFeatures) {
                return new SimpleStringProperty(flightStringCellDataFeatures.getValue().getId());
            }
        });
        twowayorigin.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Flight, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Flight, String> flightStringCellDataFeatures) {
                return new SimpleStringProperty(flightStringCellDataFeatures.getValue().getOrigin());
            }
        });
        twowaydestination.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Flight, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Flight, String> flightStringCellDataFeatures) {
                return new SimpleStringProperty(flightStringCellDataFeatures.getValue().getDestination());
            }
        });
        twowayfares.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Flight, Integer>, ObservableValue<Integer>>() {
            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Flight, Integer> flightIntegerCellDataFeatures) {
                return new SimpleIntegerProperty(flightIntegerCellDataFeatures.getValue().getFares()).asObject();
            }
        });
        twowayclass.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Flight, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Flight, String> flightStringCellDataFeatures) {
                return new SimpleStringProperty(flightStringCellDataFeatures.getValue().getClasse());
            }
        });
        twowaydeparturedate.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Flight, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Flight, String> flightStringCellDataFeatures) {
                return new SimpleStringProperty(flightStringCellDataFeatures.getValue().getDeparture_date());
            }
        });
        twowaydeparturetime.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Flight, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Flight, String> flightStringCellDataFeatures) {
                return new SimpleStringProperty(flightStringCellDataFeatures.getValue().getDeparture_time());
            }
        });
        twowayarrivaldate.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Flight, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Flight, String> flightStringCellDataFeatures) {
                return new SimpleStringProperty(flightStringCellDataFeatures.getValue().getArrivalDate());
            }
        });
        twowayarrivaltime.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Flight, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Flight, String> flightStringCellDataFeatures) {
                return new SimpleStringProperty(flightStringCellDataFeatures.getValue().getArrivalTime());
            }
        });
        twowaypassenger.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Flight, Integer>, ObservableValue<Integer>>() {
            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Flight, Integer> flightIntegerCellDataFeatures) {
                return new SimpleIntegerProperty(flightIntegerCellDataFeatures.getValue().getCapacity()).asObject();
            }
        });
        twowaytable.setItems(twoWayFlights);
    }
    public void setobservablelist()
    {
        oneWayFlights = FXCollections.observableArrayList();
        twoWayFlights = FXCollections.observableArrayList();
        ArrayList<Flight> flights = MainController.getFlightReservationSystem().getTotalflights().getFlightsschedule();
        for (int i=0;i<flights.size();i++)
        {
            if(flights.get(i) instanceof OneWayFlight)
            {
                oneWayFlights.add(flights.get(i));
            }
            else if(flights.get(i) instanceof TwoWayFlight)
            {
                twoWayFlights.add(flights.get(i));
            }
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setobservablelist();
        setonewaytableroutine();
        settwowaytableroutine();
    }
}
