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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ShowAllFlights implements Initializable {

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

    private static String data;

    final ObservableList<Flight> oneWayFlights = FXCollections.observableArrayList();
    final ObservableList<Flight> twoWayFlights = FXCollections.observableArrayList();

    @FXML
    void Backtomainmenu(ActionEvent event) {
        HelloApplication.getWindow().setHeight(420);
        HelloApplication.getWindow().setWidth(510);
        HelloApplication.getWindow().setScene(AdminFunctionsController.getManageflightsscene());
        HelloApplication.getWindow().show();
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
    private void addbutton()
    {
        TableColumn<Flight,Void> colbtn = new TableColumn("Seats");
        Callback<TableColumn<Flight,Void>, TableCell<Flight,Void>> cellfactory = new Callback<TableColumn<Flight, Void>, TableCell<Flight, Void>>() {
            @Override
            public TableCell<Flight, Void> call(TableColumn<Flight, Void> flightVoidTableColumn) {
                final TableCell<Flight,Void> cell = new TableCell<Flight,Void>()
                {
                    private final Button btn = new Button("Show");
                    {
                        btn.setStyle("-fx-background-color: #f44336; -fx-text-fill: rgba(255,255,255,0.98)");
                        btn.setOnAction((ActionEvent event) -> {
                            setData(getTableView().getItems().get(getIndex()).getId());
                            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("AdminResources/SeatsList.fxml"));
                            try {
                                Scene myDialogScene = new Scene(fxmlLoader.load(), 400, 400);
                                HelloApplication.getWindow().setWidth(430);
                                HelloApplication.getWindow().setHeight(460);
                                HelloApplication.getWindow().setScene(myDialogScene);
                                HelloApplication.getWindow().show();
                            } catch (IOException e) {
                                e.printStackTrace();
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
        Onewaytable.getColumns().add(colbtn);
    }
    private void addbutton1()
    {
        TableColumn<Flight,Void> colbtn = new TableColumn("Seats");
        Callback<TableColumn<Flight,Void>, TableCell<Flight,Void>> cellfactory = new Callback<TableColumn<Flight, Void>, TableCell<Flight, Void>>() {
            @Override
            public TableCell<Flight, Void> call(TableColumn<Flight, Void> flightVoidTableColumn) {
                final TableCell<Flight,Void> cell = new TableCell<Flight,Void>()
                {
                    private final Button btn = new Button("Show");
                    {
                        btn.setStyle("-fx-background-color: #f44336; -fx-text-fill: rgba(255,255,255,0.98)");
                        btn.setOnAction((ActionEvent event) -> {

                            setData(getTableView().getItems().get(getIndex()).getId());
                            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("AdminResources/SeatsList.fxml"));
                            try {
                                Scene myDialogScene = new Scene(fxmlLoader.load(), 400, 400);
                                HelloApplication.getWindow().setWidth(430);
                                HelloApplication.getWindow().setHeight(460);
                                HelloApplication.getWindow().setScene(myDialogScene);
                                HelloApplication.getWindow().show();
                            } catch (IOException e) {
                                e.printStackTrace();
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
        twowaytable.getColumns().add(colbtn);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
        setonewaytableroutine();
        settwowaytableroutine();
        addbutton();
        addbutton1();
    }
    public static String getData() {
        return data;
    }

    public static void setData(String data) {
        ShowAllFlights.data = data;
    }
}
