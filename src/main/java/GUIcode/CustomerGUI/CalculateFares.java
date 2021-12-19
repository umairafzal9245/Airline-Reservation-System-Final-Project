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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CalculateFares implements Initializable {

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

    @FXML
    private TextField fares;

    @FXML
    private TextField numberofpassengers;

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
                        addbutton();
                    }
                }
            }
        }
    }
    boolean addcolumn = true;
    private final String[] countries = new String[]{"Afghanistan", "Albania", "Algeria", "America", "Andorra", "Angola", "Anguilla", "Antarctica", "Antigua and Barbuda", "Argentina", "Armenia", "Aruba", "Australia", "Austria", "Azerbaijan", "Bahamas", "Behrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bermuda", "Bhutan", "Bolivia", "Bosnia", "Botswana", "Bouvet Island", "Brazil", "British Indian Ocean Territory", "Brunei Darussalam", "Bulgaria", "Burkina Faso", "Burundi", "Cambodia", "Cameroon", "Canada", "Cape Verde", "Cayman Islands", "Central African Republic", "Chad", "Chile", "China", "Christmas Island", "Cocos Islands", "Colombia", "Comoros", "Congo", "Cook Islands", "Costa Rica", "Cote d'Ivoire", "Croatia (Hrvatska)", "Cuba", "Cyprus", "Czech Republic", "Denmark", "Djibouti", "Dominica", "Dominican Republic", "East Timor", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea", "Estonia", "Ethiopia", "Falkland Islands (Malvinas)", "Faroe Islands", "Fiji", "Finland", "France", "France Metropolitan", "French Guiana", "French Polynesia", "French Southern Territories", "Gabon", "Gambia", "Georgia", "Germany", "Ghana", "Gibraltar", "Greece", "Greenland", "Grenada", "Guadeloupe", "Guam", "Guatemala", "Guinea", "Guinea-Bissau", "Guyana", "Haiti", "Heard and Mc Donald Islands", "Holy See (Vatican City State)", "Honduras", "Hong Kong", "Hungary", "Iceland", "India", "Indonesia", "Iran (Islamic Republic of)", "Iraq", "Ireland", "Israel", "Italy", "Jamaica", "Japan", "Jordan", "Kazakhstan", "Kenya", "Kiribati", "Korea", "Kuwait", "Kyrgyzstan", "Lao, People's Democratic Republic", "Latvia", "Lebanon", "Lesotho", "Liberia", "Libyan Arab Jamahiriya", "Liechtenstein", "Lithuania", "Luxembourg", "Macau", "Macedonia, The Former Yugoslav Republic of", "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands", "Martinique", "Mauritania", "Mauritius", "Mayotte", "Mexico", "Micronesia, Federated States of", "Moldova, Republic of", "Monaco", "Mongolia", "Montserrat", "Morocco", "Mozambique", "Myanmar", "Namibia", "Nauru", "Nepal", "Netherlands", "Netherlands Antilles", "New Caledonia", "New Zealand", "Nicaragua", "Niger", "Nigeria", "Niue", "Norfolk Island", "Northern Mariana Islands", "Norway", "Oman", "Pakistan", "Palau", "Panama", "Papua New Guinea", "Paraguay", "Peru", "Philippines", "Pitcairn", "Poland", "Portugal", "Puerto Rico", "Qatar", "Reunion", "Romania", "Russian Federation", "Rwanda", "Saint Kitts and Nevis", "Saint Lucia", "Saint Vincent and the Grenadines", "Samoa", "San Marino", "Sao Tome and Principe", "Saudi Arabia", "Senegal", "Seychelles", "Sierra Leone", "Singapore", "Slovakia (Slovak Republic)", "Slovenia", "Solomon Islands", "Somalia", "South Africa", "South Georgia and the South Sandwich Islands", "Spain", "Sri Lanka", "St. Helena", "St. Pierre and Miquelon", "Sudan", "Suriname", "Svalbard and Jan Mayen Islands", "Swaziland", "Sweden", "Switzerland", "Syrian Arab Republic", "Taiwan, Province of China", "Tajikistan", "Tanzania, United Republic of", "Thailand", "Togo", "Tokelau", "Tonga", "Trinidad and Tobago", "Tunisia", "Turkey", "Turkmenistan", "Turks and Caicos Islands", "Tuvalu", "Uganda", "Ukraine", "United Arab Emirates", "United Kingdom", "United States", "United States Minor Outlying Islands", "Uruguay", "Uzbekistan", "Vanuatu", "Venezuela", "Vietnam", "Virgin Islands (British)", "Virgin Islands (U.S.)", "Wallis and Futuna Islands", "Western Sahara", "Yemen", "Yugoslavia", "Zambia", "Zimbabwe", "Palestine"};
    public static String data;
    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
    private void addbutton()
    {
        TableColumn<Flight,Void> colbtn = new TableColumn("Fares");
        Callback<TableColumn<Flight,Void>, TableCell<Flight,Void>> cellfactory = new Callback<TableColumn<Flight, Void>, TableCell<Flight, Void>>() {
            @Override
            public TableCell<Flight, Void> call(TableColumn<Flight, Void> flightVoidTableColumn) {
                final TableCell<Flight,Void> cell = new TableCell<Flight,Void>()
                {
                    private final Button btn = new Button("Check");
                    {
                        btn.setStyle("-fx-background-color: #f44336; -fx-text-fill: rgba(255,255,255,0.98)");
                        btn.setOnAction((ActionEvent event) -> {
                            data = getTableView().getItems().get(getIndex()).getId();
                            if(numberofpassengers.getText() == null || numberofpassengers.getText().length() == 0 || !isNumeric(numberofpassengers.getText()) || Integer.parseInt(numberofpassengers.getText()) == 0 || Integer.parseInt(numberofpassengers.getText()) > MainController.getFlightReservationSystem().getTotalflights().getFlight(data).getCapacity())
                            {
                                numberofpassengers.setStyle("-fx-background-color: transparent; -fx-border-color: red; -fx-border-width: 0px 0px 2px 0px;");
                            }
                            else
                            {
                                numberofpassengers.setStyle("-fx-background-color: transparent; -fx-border-color: #0598ff; -fx-border-width: 0px 0px 2px 0px;");
                                int pass = Integer.parseInt(numberofpassengers.getText());
                                int getfare = MainController.getFlightReservationSystem().getTotalflights().getFlight(data).getFares();
                                fares.setText(Integer.toString(pass*getfare)+"$");
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

        if(addcolumn)
            Table.getColumns().add(colbtn);

        addcolumn = false;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> origin2 = FXCollections.observableArrayList();
        ObservableList<String> destination2 = FXCollections.observableArrayList();
        ObservableList<String> flt = FXCollections.observableArrayList("Oneway","Roundtrip");
        for (String item:countries)
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
