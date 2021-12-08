package GUIcode.CustomerGUI;

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
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ShowReservations implements Initializable {

    @FXML
    private TableView<Reservation> Table;

    @FXML
    private TableColumn<Reservation, Integer> bookingreference;

    @FXML
    private TableColumn<Reservation, String> customerpassport;

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

    final ObservableList<Reservation> reservationlist = FXCollections.observableArrayList();

    private void addbutton()
    {
        TableColumn<Reservation,Void> colbtn = new TableColumn("View Ticket");
        Callback<TableColumn<Reservation,Void>, TableCell<Reservation,Void>> cellfactory = new Callback<TableColumn<Reservation, Void>, TableCell<Reservation, Void>>() {
            @Override
            public TableCell<Reservation, Void> call(TableColumn<Reservation, Void> flightVoidTableColumn) {
                final TableCell<Reservation,Void> cell = new TableCell<Reservation,Void>()
                {
                    private final Button btn = new Button("Show");
                    {
                        btn.setOnAction((ActionEvent event) -> {
                            ref = getTableView().getItems().get(getIndex()).getBookingreference();
                            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("PrintTicket.fxml"));
                            try {
                                Scene myDialogScene = new Scene(fxmlLoader.load(), 500, 500);
                                HelloApplication.window.setScene(myDialogScene);
                                HelloApplication.window.show();
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
        Table.getColumns().add(colbtn);
    }

    @FXML
    void BackToMenu(ActionEvent event) {
        HelloApplication.window.setScene(CustomerLoginScene.Customerfunctionsscene);
        HelloApplication.window.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        ArrayList<Reservation> reservationarray = MainController.flightReservationSystem.GetReservations();
        reservationlist.addAll(reservationarray);
        bookingreference.setCellValueFactory(new PropertyValueFactory<Reservation,Integer>("bookingreference"));
        flightid.setCellValueFactory(new PropertyValueFactory<Reservation,String>("flightid"));
        customerpassport.setCellValueFactory(new PropertyValueFactory<Reservation,String>("customerpassport"));
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
        addbutton();
        Table.setItems(reservationlist);
    }
}
