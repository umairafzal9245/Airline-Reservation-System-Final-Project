package GUIcode.CustomerGUI;

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
import javafx.scene.control.*;
import javafx.util.Callback;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class BookSeats implements Initializable {

    @FXML
    private ChoiceBox<String> expirymonth;

    @FXML
    private ChoiceBox<String> expiryyear;

    @FXML
    private TextField cardname;

    @FXML
    private TextField cardnumber;

    @FXML
    private TextField cvv;

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

    private static final ArrayList<Integer> selectedseats = new ArrayList<>();
    private final ObservableList<Seats> seatslist = FXCollections.observableArrayList();

    @FXML
    void Exit(ActionEvent event) {
        HelloApplication.getWindow().setScene(CustomerFunctions.getBookflight());
        HelloApplication.getWindow().show();
    }

    @FXML
    void Bookflight(ActionEvent event) {
        if (cardname == null || cardname.getText().length() == 0)
            cardname.setStyle("-fx-border-color: red ; -fx-border-width: 2px;");
        else {
            cardname.setStyle("fx-border-width: 0px");
            String cardn = cardname.getText();
            if(cardnumber == null || cardnumber.getText().length() == 0)
                cardnumber.setStyle("-fx-border-color: red ; -fx-border-width: 2px;");
            else
            {
                cardnumber.setStyle("fx-border-width: 0px");
                String carnum = cardnumber.getText();
                if(cvv == null || cvv.getText().length() > 3 || cvv.getText().length() == 0 || !isNumeric(cvv.getText()))
                    cvv.setStyle("-fx-border-color: red ; -fx-border-width: 2px;");
                else
                {
                    cvv.setStyle("fx-border-width: 0px");
                    Integer cv = Integer.parseInt(cvv.getText());
                    if(expirymonth.getValue() == null)
                        expirymonth.setStyle("-fx-border-color: red ; -fx-border-width: 2px;");
                    else
                    {
                        expirymonth.setStyle("fx-border-width: 0px");
                        String expmon = expirymonth.getValue();
                        if(expiryyear.getValue() == null)
                            expiryyear.setStyle("-fx-border-color: red ; -fx-border-width: 2px;");
                        else
                        {
                            expiryyear.setStyle("fx-border-width: 0px");
                            String expyear = expiryyear.getValue();
                            if(getSelectedseats().size() == 0)
                            {
                                Alert message = new Alert(Alert.AlertType.ERROR);
                                message.setTitle("Select Seats");
                                message.setContentText("Please Select Seats");
                                message.showAndWait();
                            }
                            else
                            {
                                String expirydate = expmon + "/" +expyear;
                                boolean flag = false;
                                int rf = 0;
                                try {
                                    rf = MainController.getFlightReservationSystem().BookFlight(BookFlight.data, getSelectedseats().size(), getSelectedseats(), cardn, carnum, expirydate, cv);
                                    flag = true;
                                }
                                catch (Exception e)
                                {
                                    Alert message = new Alert(Alert.AlertType.ERROR);
                                    message.setTitle("Error");
                                    message.setContentText(e.getMessage());
                                    message.showAndWait();
                                }
                                if(flag)
                                {
                                    Alert message = new Alert(Alert.AlertType.INFORMATION);
                                    message.setTitle("Successfully");
                                    message.setContentText("Seats Successfully booked, Booking Reference = "+rf+" Ticket Generated");
                                    message.showAndWait();
                                    HelloApplication.getWindow().setScene(CustomerFunctions.getBookflight());
                                    HelloApplication.getWindow().show();
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

    private void addbutton()
    {
        TableColumn<Seats,Void> colbtn = new TableColumn("Action");
        Callback<TableColumn<Seats,Void>, TableCell<Seats,Void>> cellfactory = new Callback<TableColumn<Seats, Void>, TableCell<Seats, Void>>() {
            @Override
            public TableCell<Seats, Void> call(TableColumn<Seats, Void> flightVoidTableColumn) {
                final TableCell<Seats,Void> cell = new TableCell<Seats,Void>()
                {
                    private final Button btn = new Button("Select");
                    {
                        btn.setOnAction((ActionEvent event) -> {
                            int data = getTableView().getItems().get(getIndex()).getNumber();
                            if(btn.getText().equalsIgnoreCase("select"))
                            {
                                if(!getTableView().getItems().get(getIndex()).getStatus().equalsIgnoreCase("booked"))
                                {
                                    btn.setText("Selected");
                                    getSelectedseats().add(data);
                                }
                            }
                            else if(btn.getText().equalsIgnoreCase("selected"))
                            {
                                for (int i = 0; i< getSelectedseats().size(); i++)
                                    if(getSelectedseats().get(i) == data)
                                        getSelectedseats().remove(i);

                                btn.setText("Select");
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
        seatstable.getColumns().add(colbtn);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ObservableList<String> month = FXCollections.observableArrayList();
        ObservableList<String> year = FXCollections.observableArrayList();
        for (int i=1;i<=12;i++)
            month.add(""+i);

        for (int i=0;i<=20;i++)
            year.add(Integer.toString(21+i));

        expirymonth.setItems(month);
        expiryyear.setItems(year);

        ArrayList<Seats> getseats = null;
        try {
            getseats = MainController.getFlightReservationSystem().getTotalflights().GetFlightSeats(BookFlight.data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        getSeatslist().addAll(getseats);

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
        seatstable.setItems(getSeatslist());
        addbutton();
    }

    public ObservableList<Seats> getSeatslist() {
        return seatslist;
    }
    public static ArrayList<Integer> getSelectedseats() {
        return selectedseats;
    }
}
