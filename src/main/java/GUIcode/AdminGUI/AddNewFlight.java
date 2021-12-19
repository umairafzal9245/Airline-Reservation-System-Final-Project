package GUIcode.AdminGUI;

import GUIcode.HelloApplication;
import GUIcode.MainController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Callback;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddNewFlight implements Initializable {

    @FXML
    private DatePicker arrivaldate;

    @FXML
    private Spinner<Integer> arrivalhours;

    @FXML
    private Spinner<Integer> arrivalminutes;

    @FXML
    private ChoiceBox<String> arrivalzone;

    @FXML
    private ChoiceBox<String> classe;

    @FXML
    private DatePicker departuredate;

    @FXML
    private Spinner<Integer> departurehours;

    @FXML
    private Spinner<Integer> departureminutes;

    @FXML
    private ChoiceBox<String> departurezone;

    @FXML
    private ComboBox<String> destination;

    @FXML
    private TextField fares;

    @FXML
    private TextField flightid;

    @FXML
    private ChoiceBox<String> flighttype;

    @FXML
    private ComboBox<String> origin;

    @FXML
    private TextField capacity;

    @FXML
    void AddFlight(ActionEvent event) {

        String id = flightid.getText();
        if (id.length() == 0)
            flightid.setStyle("-fx-background-color: transparent; -fx-border-color: red; -fx-border-width: 0px 0px 2px 0px;");
        else
        {
            flightid.setStyle("-fx-background-color: transparent; -fx-border-color: #0598ff; -fx-border-width: 0px 0px 2px 0px;");
            if (origin.getSelectionModel().getSelectedItem() == null)
                origin.setStyle("-fx-background-color: transparent; -fx-border-color: red;");
            else
            {
                origin.setStyle("-fx-background-color: transparent; -fx-border-color: #0598ff;");
                String org = origin.getSelectionModel().getSelectedItem();

                if(destination.getSelectionModel().getSelectedItem() == null)
                    destination.setStyle("-fx-background-color: transparent; -fx-border-color: red; ");
                else {
                    destination.setStyle("-fx-background-color: transparent; -fx-border-color: #0598ff; ");
                    String dest = destination.getSelectionModel().getSelectedItem();

                    if (dest.equalsIgnoreCase(org)) {
                        destination.setStyle("-fx-background-color: transparent; -fx-border-color: red; ");
                        origin.setStyle("-fx-background-color: transparent; -fx-border-color: red; ");
                    }
                    else
                    {
                        origin.setStyle("-fx-background-color: transparent; -fx-border-color: #0598ff;");
                        destination.setStyle("-fx-background-color: transparent; -fx-border-color: #0598ff; ");
                        dest = destination.getSelectionModel().getSelectedItem();
                        org = origin.getSelectionModel().getSelectedItem();

                        String capa = capacity.getText();
                        if (capa == null || capa.length() == 0 || !isNumeric(capa))
                            capacity.setStyle("-fx-background-color: transparent; -fx-border-color: red; -fx-border-width: 0px 0px 2px 0px;");
                        else {
                            capacity.setStyle("-fx-background-color: transparent; -fx-border-color: #0598ff; -fx-border-width: 0px 0px 2px 0px;");
                            int capac = Integer.parseInt(capa);
                            if (capac < 5) {
                                capacity.setText("Greater than 5");
                                capacity.setStyle("-fx-background-color: transparent; -fx-border-color: red; -fx-border-width: 0px 0px 2px 0px;");
                            } else {
                                String far = fares.getText();
                                if (far == null || far.length() == 0 || !isNumeric(far))
                                    fares.setStyle("-fx-background-color: transparent; -fx-border-color: red; -fx-border-width: 0px 0px 2px 0px;");
                                else {
                                    fares.setStyle("-fx-background-color: transparent; -fx-border-color: #0598ff; -fx-border-width: 0px 0px 2px 0px;");
                                    int fare = Integer.parseInt(far);

                                    String cl = classe.getSelectionModel().getSelectedItem();
                                    String typ = flighttype.getSelectionModel().getSelectedItem();

                                    if (departuredate.getValue() == null)
                                        departuredate.setStyle("-fx-background-color: transparent; -fx-border-color: red; ");
                                    else {
                                        departuredate.setStyle("-fx-background-color: transparent; -fx-border-color: #0598ff; ");
                                        LocalDate depdate = departuredate.getValue();
                                        String dept_date = depdate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                                        String deptime = String.format("%02d", departurehours.getValue()) + ":" + String.format("%02d", departureminutes.getValue()) + " " + departurezone.getValue();
                                        if (typ.equalsIgnoreCase("Roundtrip")) {
                                            if (arrivaldate.getValue() == null)
                                                arrivaldate.setStyle("-fx-background-color: transparent; -fx-border-color: red; ");
                                            else {
                                                arrivaldate.setStyle("-fx-background-color: transparent; -fx-border-color: #0598ff;");
                                                LocalDate arrdate = arrivaldate.getValue();
                                                String arr_date = arrdate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                                                if(arr_date.equalsIgnoreCase(dept_date))
                                                {
                                                    arrivaldate.setStyle("-fx-background-color: transparent; -fx-border-color: red; ");
                                                    departuredate.setStyle("-fx-background-color: transparent; -fx-border-color: red; ");
                                                }
                                                else {
                                                    arrivaldate.setStyle("-fx-background-color: transparent; -fx-border-color: #0598ff; ");
                                                    departuredate.setStyle("-fx-background-color: transparent; -fx-border-color: #0598ff; ");
                                                    String arrtime = String.format("%02d", arrivalhours.getValue()) + ":" + String.format("%02d", arrivalminutes.getValue()) + " " + arrivalzone.getValue();
                                                    boolean flag = false;
                                                    try {
                                                        MainController.getFlightReservationSystem().getTotalflights().ChooseandAddFlight(id, org, dest, capac, fare, cl, typ, dept_date, deptime, arr_date, arrtime);
                                                        flag = true;
                                                    } catch (Exception e) {
                                                        Alert message = new Alert(Alert.AlertType.ERROR);
                                                        message.setTitle("Duplicate Flight");
                                                        message.setContentText("Flight with this ID already present");
                                                        message.showAndWait();
                                                    }
                                                    if (flag) {
                                                        Alert message = new Alert(Alert.AlertType.INFORMATION);
                                                        message.setTitle("Flight Added");
                                                        message.setContentText("Flight Successfully added");
                                                        message.showAndWait();
                                                    }
                                                }
                                            }
                                        } else {
                                            boolean flag = false;
                                            try {
                                                MainController.getFlightReservationSystem().getTotalflights().ChooseandAddFlight(id, org, dest, capac, fare, cl, typ, dept_date, deptime, "", "");
                                                flag = true;
                                            } catch (Exception e) {
                                                Alert message = new Alert(Alert.AlertType.ERROR);
                                                message.setTitle("Duplicate Flight");
                                                message.setContentText("Flight with this ID already present");
                                                message.showAndWait();
                                            }
                                            if (flag) {
                                                Alert message = new Alert(Alert.AlertType.INFORMATION);
                                                message.setTitle("Flight Added");
                                                message.setContentText("Flight Successfully added");
                                                message.showAndWait();
                                            }
                                        }
                                    }
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

    @FXML
    void Back() {
        HelloApplication.getWindow().setHeight(420);
        HelloApplication.getWindow().setWidth(510);
        HelloApplication.getWindow().setScene(AdminFunctionsController.getManageflightsscene());
        HelloApplication.getWindow().show();
    }

    @FXML
    void Disabletwoway(ActionEvent event)
    {
        if(flighttype.getValue().equalsIgnoreCase("oneway"))
        {
            arrivaldate.setDisable(true);
            arrivalzone.setDisable(true);
            arrivalminutes.setDisable(true);
            arrivalhours.setDisable(true);
        }
        else if(flighttype.getValue().equalsIgnoreCase("roundtrip"))
        {
            arrivaldate.setDisable(false);
            arrivalzone.setDisable(false);
            arrivalminutes.setDisable(false);
            arrivalhours.setDisable(false);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Callback<DatePicker, DateCell> callB = new Callback<DatePicker, DateCell>() {
            @Override
            public DateCell call(final DatePicker param) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        LocalDate today = LocalDate.now();
                        setDisable(empty || item.compareTo(today) < 0);
                    }

                };
            }

        };
        departuredate.setDayCellFactory(callB);
        arrivaldate.setDayCellFactory(callB);

        departurehours.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1,12,1));
        departureminutes.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,59,0));
        arrivalhours.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1,12,1));
        arrivalminutes.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,59,0));

        ObservableList<String> origin2 = FXCollections.observableArrayList();
        ObservableList<String> destination2 = FXCollections.observableArrayList();
        ObservableList<String> clas = FXCollections.observableArrayList("First","Economy","Business");
        ObservableList<String> flt = FXCollections.observableArrayList("Oneway","Roundtrip");
        ObservableList<String> zones = FXCollections.observableArrayList("AM", "PM");
        for (String item:MainController.getCountries())
        {
            origin2.add(item);
            destination2.add(item);
        }
        origin.setItems(origin2);
        destination.setItems(destination2);
        classe.setItems(clas);
        classe.setValue("First");
        flighttype.setItems(flt);
        flighttype.setValue("oneway");
        departurezone.setItems(zones);
        departurezone.setValue("AM");
        arrivalzone.setItems(zones);
        arrivalzone.setValue("AM");
    }
}
