package GUIcode.CustomerGUI;

import BusinessLogic.Flight;
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

public class SearchFlight implements Initializable {

    @FXML
    private ComboBox<String> Destination;

    @FXML
    private ChoiceBox<String> flighttype;

    @FXML
    private ComboBox<String> Originn;

    @FXML
    private TableColumn<Flight, String> Id;

    @FXML
    private TableColumn<Flight, String> Orig;

    @FXML
    private TableView<Flight> Table;

    @FXML
    private TableColumn<Flight, String> arrivaldate;

    @FXML
    private TableColumn<Flight, String> arrivaltime;

    @FXML
    private TableColumn<Flight, Integer> capacity;

    @FXML
    private TableColumn<Flight, String> classe;

    @FXML
    private TableColumn<Flight, String> departuredate;

    @FXML
    private TableColumn<Flight, String> departuretime;

    @FXML
    private TableColumn<Flight, String> destination;

    @FXML
    private TableColumn<Flight, Integer> fare;


    private ObservableList<Flight> flight = FXCollections.observableArrayList();

    @FXML
    void SelectFlightTable(ActionEvent event)
    {
        if(flighttype.getValue().equalsIgnoreCase("oneway")) {
            Table.getColumns().get(6).setVisible(false);
            Table.getColumns().get(7).setVisible(false);
        }
        else if(flighttype.getValue().equalsIgnoreCase("roundtrip"))
        {
            Table.getColumns().get(6).setVisible(true);
            Table.getColumns().get(7).setVisible(true);
        }
    }
    @FXML
    void Back()
    {
        HelloApplication.getWindow().setWidth(700);
        HelloApplication.getWindow().setHeight(540);
        HelloApplication.getWindow().setScene(CustomerLoginScene.getCustomerfunctionsscene());
        HelloApplication.getWindow().show();
    }
    @FXML
    void SearchFlight(ActionEvent event) {
        if(Originn.getValue() == null || Originn.getValue().length() == 0)
        {
            Originn.setStyle("-fx-background-color: transparent; -fx-border-color: red;");
        }
        else {
            Originn.setStyle("-fx-background-color: transparent; -fx-border-color: #0598ff;");
            if (Destination.getValue() == null || Destination.getValue().length() == 0) {
                Destination.setStyle("-fx-background-color: transparent; -fx-border-color: red;");
            } else {
                Destination.setStyle("-fx-background-color: transparent; -fx-border-color: #0598ff;");
                if (Originn.getValue() == Destination.getValue()) {
                    Destination.setStyle("-fx-background-color: transparent; -fx-border-color: red;");
                    Originn.setStyle("-fx-background-color: transparent; -fx-border-color: red;");
                } else {
                    Originn.setStyle("-fx-background-color: transparent; -fx-border-color: #0598ff;");
                    Destination.setStyle("-fx-background-color: transparent; -fx-border-color: #0598ff;");
                    boolean found = false;
                    ArrayList<Flight> flightlist = null;
                    try {
                        flightlist = MainController.getFlightReservationSystem().getTotalflights().searchFlights(Originn.getValue(), Destination.getValue(), flighttype.getValue());
                        found = true;
                    } catch (Exception e) {
                        Alert message = new Alert(Alert.AlertType.ERROR);
                        message.setTitle("No flight found");
                        message.setContentText("No flight found");
                        message.showAndWait();
                    }
                    if (found) {
                        flight = FXCollections.observableArrayList();
                        flight.addAll(flightlist);
                        Id.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Flight, String>, ObservableValue<String>>() {
                            @Override
                            public ObservableValue<String> call(TableColumn.CellDataFeatures<Flight, String> flightStringCellDataFeatures) {
                                return new SimpleStringProperty(flightStringCellDataFeatures.getValue().getId());
                            }
                        });
                        destination.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Flight, String>, ObservableValue<String>>() {
                            @Override
                            public ObservableValue<String> call(TableColumn.CellDataFeatures<Flight, String> flightStringCellDataFeatures) {
                                return new SimpleStringProperty(Destination.getValue());
                            }
                        });
                        Orig.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Flight, String>, ObservableValue<String>>() {
                            @Override
                            public ObservableValue<String> call(TableColumn.CellDataFeatures<Flight, String> flightStringCellDataFeatures) {
                                return new SimpleStringProperty(Originn.getValue());
                            }
                        });
                        fare.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Flight, Integer>, ObservableValue<Integer>>() {
                            @Override
                            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Flight, Integer> flightIntegerCellDataFeatures) {
                                return new SimpleIntegerProperty(flightIntegerCellDataFeatures.getValue().getFares()).asObject();
                            }
                        });
                        capacity.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Flight, Integer>, ObservableValue<Integer>>() {
                            @Override
                            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Flight, Integer> flightIntegerCellDataFeatures) {
                                return new SimpleIntegerProperty(flightIntegerCellDataFeatures.getValue().getCapacity()).asObject();
                            }
                        });
                        departuredate.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Flight, String>, ObservableValue<String>>() {
                            @Override
                            public ObservableValue<String> call(TableColumn.CellDataFeatures<Flight, String> flightStringCellDataFeatures) {
                                return new SimpleStringProperty(flightStringCellDataFeatures.getValue().getDeparture_date());
                            }
                        });
                        departuretime.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Flight, String>, ObservableValue<String>>() {
                            @Override
                            public ObservableValue<String> call(TableColumn.CellDataFeatures<Flight, String> flightStringCellDataFeatures) {
                                return new SimpleStringProperty(flightStringCellDataFeatures.getValue().getDeparture_time());
                            }
                        });
                        arrivaldate.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Flight, String>, ObservableValue<String>>() {
                            @Override
                            public ObservableValue<String> call(TableColumn.CellDataFeatures<Flight, String> flightStringCellDataFeatures) {
                                return new SimpleStringProperty(flightStringCellDataFeatures.getValue().getArrivalDate());
                            }
                        });
                        arrivaltime.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Flight, String>, ObservableValue<String>>() {
                            @Override
                            public ObservableValue<String> call(TableColumn.CellDataFeatures<Flight, String> flightStringCellDataFeatures) {
                                return new SimpleStringProperty(flightStringCellDataFeatures.getValue().getArrivalTime());
                            }
                        });
                        classe.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Flight, String>, ObservableValue<String>>() {
                            @Override
                            public ObservableValue<String> call(TableColumn.CellDataFeatures<Flight, String> flightStringCellDataFeatures) {
                                return new SimpleStringProperty(flightStringCellDataFeatures.getValue().getClasse());
                            }
                        });
                        Table.setItems(flight);
                    }
                }
            }
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> origin2 = FXCollections.observableArrayList();
        ObservableList<String> destination2 = FXCollections.observableArrayList();
        ObservableList<String> flt = FXCollections.observableArrayList("Oneway","Roundtrip");
        for (String item:MainController.getCountries())
        {
            origin2.add(item);
            destination2.add(item);
        }
        Originn.setItems(origin2);
        Destination.setItems(destination2);
        flighttype.setItems(flt);
        flighttype.setValue("oneway");
    }
}
