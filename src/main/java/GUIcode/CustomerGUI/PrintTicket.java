package GUIcode.CustomerGUI;

import BusinessLogic.Flight;
import BusinessLogic.FlightReservationSystem;
import BusinessLogic.Reservation;
import GUIcode.HelloApplication;
import GUIcode.MainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class PrintTicket implements Initializable {

    @FXML
    private TextField arrivaldate;

    @FXML
    private TextField arrivaltime;

    @FXML
    private TextField bookingref;

    @FXML
    private TextField classe;

    @FXML
    private TextField customername;

    @FXML
    private TextField deptdate;

    @FXML
    private TextField depttime;

    @FXML
    private TextField fare;

    @FXML
    private TextField flightid;

    @FXML
    private TextField numberofpassenger;

    @FXML
    private TextField typee;

    @FXML
    private Label arrivdate;

    @FXML
    private Label arrivtime;

    @FXML
    void Back(ActionEvent event) {
        HelloApplication.window.setScene(CustomerFunctions.showreservations);
        HelloApplication.window.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Integer ref = ShowReservations.ref;
        Reservation object = MainController.flightReservationSystem.reservations.getReservation(ref);
        bookingref.setText(ref.toString());
        flightid.setText(object.getFlightid());
        customername.setText(object.getCustomername());
        numberofpassenger.setText(Integer.toString(object.getTicket().getNumberofpassengers()));
        fare.setText(Integer.toString(object.getTicket().getTotalfares()));
        classe.setText(object.getTicket().getType());
        Flight object2 = MainController.flightReservationSystem.totalflights.getFlight(object.getFlightid());
        flightid.setText(object2.getId());
        typee.setText(object2.getType());
        if(object2.getType().equalsIgnoreCase("oneway"));
        {
            deptdate.setText(object2.getDeparture_date());
            depttime.setText(object2.getDeparture_time());
            arrivaldate.setVisible(false);
            arrivaltime.setVisible(false);
            arrivdate.setVisible(false);
            arrivtime.setVisible(false);
        }
        if(object2.getType().equalsIgnoreCase("roundtrip"))
        {
            arrivaldate.setVisible(true);
            arrivaltime.setVisible(true);
            arrivaldate.setText(object2.getArrivalDate());
            arrivaltime.setText(object2.getArrivalTime());
            arrivdate.setVisible(true);
            arrivdate.setVisible(true);
        }
    }
}
