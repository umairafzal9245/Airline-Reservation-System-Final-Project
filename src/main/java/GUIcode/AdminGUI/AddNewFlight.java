package GUIcode.AdminGUI;

import GUIcode.HelloApplication;
import GUIcode.MainController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

    private String[] countries = new String[]{"Afghanistan", "Albania", "Algeria", "American Samoa", "Andorra", "Angola", "Anguilla", "Antarctica", "Antigua and Barbuda", "Argentina", "Armenia", "Aruba", "Australia", "Austria", "Azerbaijan", "Bahamas", "Behrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bermuda", "Bhutan", "Bolivia", "Bosnia", "Botswana", "Bouvet Island", "Brazil", "British Indian Ocean Territory", "Brunei Darussalam", "Bulgaria", "Burkina Faso", "Burundi", "Cambodia", "Cameroon", "Canada", "Cape Verde", "Cayman Islands", "Central African Republic", "Chad", "Chile", "China", "Christmas Island", "Cocos Islands", "Colombia", "Comoros", "Congo", "Cook Islands", "Costa Rica", "Cote d'Ivoire", "Croatia (Hrvatska)", "Cuba", "Cyprus", "Czech Republic", "Denmark", "Djibouti", "Dominica", "Dominican Republic", "East Timor", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea", "Estonia", "Ethiopia", "Falkland Islands (Malvinas)", "Faroe Islands", "Fiji", "Finland", "France", "France Metropolitan", "French Guiana", "French Polynesia", "French Southern Territories", "Gabon", "Gambia", "Georgia", "Germany", "Ghana", "Gibraltar", "Greece", "Greenland", "Grenada", "Guadeloupe", "Guam", "Guatemala", "Guinea", "Guinea-Bissau", "Guyana", "Haiti", "Heard and Mc Donald Islands", "Holy See (Vatican City State)", "Honduras", "Hong Kong", "Hungary", "Iceland", "India", "Indonesia", "Iran (Islamic Republic of)", "Iraq", "Ireland", "Israel", "Italy", "Jamaica", "Japan", "Jordan", "Kazakhstan", "Kenya", "Kiribati", "Korea", "Kuwait", "Kyrgyzstan", "Lao, People's Democratic Republic", "Latvia", "Lebanon", "Lesotho", "Liberia", "Libyan Arab Jamahiriya", "Liechtenstein", "Lithuania", "Luxembourg", "Macau", "Macedonia, The Former Yugoslav Republic of", "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands", "Martinique", "Mauritania", "Mauritius", "Mayotte", "Mexico", "Micronesia, Federated States of", "Moldova, Republic of", "Monaco", "Mongolia", "Montserrat", "Morocco", "Mozambique", "Myanmar", "Namibia", "Nauru", "Nepal", "Netherlands", "Netherlands Antilles", "New Caledonia", "New Zealand", "Nicaragua", "Niger", "Nigeria", "Niue", "Norfolk Island", "Northern Mariana Islands", "Norway", "Oman", "Pakistan", "Palau", "Panama", "Papua New Guinea", "Paraguay", "Peru", "Philippines", "Pitcairn", "Poland", "Portugal", "Puerto Rico", "Qatar", "Reunion", "Romania", "Russian Federation", "Rwanda", "Saint Kitts and Nevis", "Saint Lucia", "Saint Vincent and the Grenadines", "Samoa", "San Marino", "Sao Tome and Principe", "Saudi Arabia", "Senegal", "Seychelles", "Sierra Leone", "Singapore", "Slovakia (Slovak Republic)", "Slovenia", "Solomon Islands", "Somalia", "South Africa", "South Georgia and the South Sandwich Islands", "Spain", "Sri Lanka", "St. Helena", "St. Pierre and Miquelon", "Sudan", "Suriname", "Svalbard and Jan Mayen Islands", "Swaziland", "Sweden", "Switzerland", "Syrian Arab Republic", "Taiwan, Province of China", "Tajikistan", "Tanzania, United Republic of", "Thailand", "Togo", "Tokelau", "Tonga", "Trinidad and Tobago", "Tunisia", "Turkey", "Turkmenistan", "Turks and Caicos Islands", "Tuvalu", "Uganda", "Ukraine", "United Arab Emirates", "United Kingdom", "United States", "United States Minor Outlying Islands", "Uruguay", "Uzbekistan", "Vanuatu", "Venezuela", "Vietnam", "Virgin Islands (British)", "Virgin Islands (U.S.)", "Wallis and Futuna Islands", "Western Sahara", "Yemen", "Yugoslavia", "Zambia", "Zimbabwe", "Palestine"};

    @FXML
    void AddFlight(ActionEvent event) {

        String id = flightid.getText();
        if (id.length() == 0)
            flightid.setStyle("-fx-border-color: red ; -fx-border-width: 2px;");
        else
        {
            flightid.setStyle("fx-border-width: 0px");
            if (origin.getSelectionModel().getSelectedItem() == null)
                origin.setStyle("-fx-border-color: red ; -fx-border-width: 2px;");
            else
            {
                origin.setStyle("fx-border-width: 0px");
                String org = origin.getSelectionModel().getSelectedItem();

                if(destination.getSelectionModel().getSelectedItem() == null)
                    destination.setStyle("-fx-border-color: red ; -fx-border-width: 2px;");
                else
                {
                    destination.setStyle("fx-border-width: 0px");
                    String dest = destination.getSelectionModel().getSelectedItem();

                    String capa = capacity.getText();
                    if(capa == null || capa.length() == 0 || !isNumeric(capa))
                        capacity.setStyle("-fx-border-color: red ; -fx-border-width: 2px;");
                        else
                        {
                        capacity.setStyle("fx-border-width: 0px");
                        Integer capac = Integer.parseInt(capa);
                        if (capac < 5)
                        {
                            capacity.setText("Greater than 5");
                            capacity.setStyle("-fx-border-color: red ; -fx-border-width: 2px;");
                        }
                        else
                        {
                            String far = fares.getText();
                            if (far == null || far.length() == 0 || !isNumeric(far))
                                fares.setStyle("-fx-border-color: red ; -fx-border-width: 2px;");
                            else
                            {
                                fares.setStyle("fx-border-width: 0px");
                                Integer fare = Integer.parseInt(far);

                                String cl = classe.getSelectionModel().getSelectedItem();
                                String typ = flighttype.getSelectionModel().getSelectedItem();

                                if (departuredate.getValue() == null)
                                    departuredate.setStyle("-fx-border-color: red ; -fx-border-width: 2px;");
                                else
                                {
                                    departuredate.setStyle("fx-border-width: 0px");
                                    LocalDate depdate = departuredate.getValue();
                                    String dept_date = depdate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                                    String deptime = String.format("%02d", departurehours.getValue()) + ":" + String.format("%02d", departureminutes.getValue()) + " " + departurezone.getValue();
                                    if (typ.equalsIgnoreCase("Roundtrip"))
                                    {
                                        if (arrivaldate.getValue() == null)
                                            arrivaldate.setStyle("-fx-border-color: red ; -fx-border-width: 2px;");
                                        else
                                        {
                                            arrivaldate.setStyle("fx-border-width: 0px");
                                            LocalDate arrdate = arrivaldate.getValue();
                                            String arr_date = depdate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                                            String arrtime = String.format("%02d", arrivalhours.getValue()) + ":" + String.format("%02d", arrivalminutes.getValue()) + " " + arrivalzone.getValue();
                                            boolean flag = false;
                                            try {
                                                MainController.flightReservationSystem.totalflights.ChooseandAddFlight(id,org,dest,capac,fare,cl,typ,dept_date,deptime,arr_date,arrtime);
                                                flag = true;
                                            }
                                            catch (Exception e)
                                            {
                                                Alert message = new Alert(Alert.AlertType.ERROR);
                                                message.setTitle("Duplicate Flight");
                                                message.setContentText("Flight with this ID already present");
                                                message.showAndWait();
                                            }
                                            if (flag)
                                            {
                                                Alert message = new Alert(Alert.AlertType.INFORMATION);
                                                message.setTitle("Flight Added");
                                                message.setContentText("Flight Successfully added");
                                                message.showAndWait();
                                            }

                                        }
                                    }
                                    else
                                    {
                                        boolean flag = false;
                                        try {
                                            MainController.flightReservationSystem.totalflights.ChooseandAddFlight(id,org,dest,capac,fare,cl,typ,dept_date,deptime,"","");
                                            flag = true;
                                        }
                                        catch (Exception e)
                                        {
                                            Alert message = new Alert(Alert.AlertType.ERROR);
                                            message.setTitle("Duplicate Flight");
                                            message.setContentText("Flight with this ID already present");
                                            message.showAndWait();
                                        }
                                        if (flag)
                                        {
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

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    @FXML
    void Back(ActionEvent event) {
        HelloApplication.window.setScene(AdminFunctionsController.manageflightsscene);
        HelloApplication.window.show();
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

        departurehours.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1,12,1));
        departureminutes.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,59,0));
        arrivalhours.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1,12,1));
        arrivalminutes.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,59,0));

        ObservableList<String> origin2 = FXCollections.observableArrayList();
        ObservableList<String> destination2 = FXCollections.observableArrayList();
        ObservableList<String> clas = FXCollections.observableArrayList("First","Economy","Business");
        ObservableList<String> flt = FXCollections.observableArrayList("Oneway","Roundtrip");
        ObservableList<String> zones = FXCollections.observableArrayList("AM", "PM");
        for (String item:countries)
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
