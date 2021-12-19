package GUIcode.CustomerGUI;

import BusinessLogic.Flight;
import BusinessLogic.Reservation;
import GUIcode.HelloApplication;
import GUIcode.MainController;
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
    private TextField customerpassport;

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
    private TextField bookdateandtime;

    @FXML
    void Back() {
        HelloApplication.getWindow().setHeight(540);
        HelloApplication.getWindow().setWidth(860);
        HelloApplication.getWindow().setScene(CustomerFunctions.getShowreservations());
        HelloApplication.getWindow().show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Integer ref = ShowReservations.getRef();
        Reservation object = MainController.getFlightReservationSystem().getReservations().getReservation(ref);
        bookingref.setText(ref.toString());
        flightid.setText(object.getFlightid());
        customerpassport.setText(object.getCustomerPassport().toString());
        numberofpassenger.setText(Integer.toString(object.getTicket().getNumberofpassengers()));
        fare.setText(Integer.toString(object.getTicket().getTotalfares())+"$");
        classe.setText(object.getTicket().getType());
        Flight object2 = MainController.getFlightReservationSystem().getTotalflights().getFlight(object.getFlightid());
        flightid.setText(object2.getId());
        typee.setText(object2.getType());
        bookdateandtime.setText(object.getTicket().getBookingdateandtime());
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
            arrivtime.setVisible(true);
        }
    }
}
