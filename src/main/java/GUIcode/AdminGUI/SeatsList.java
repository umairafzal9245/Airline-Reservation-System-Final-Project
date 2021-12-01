package GUIcode.AdminGUI;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class SeatsList implements Initializable {

    @FXML
    private Label flightid;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        flightid.setText(ShowAllFlights.data);
    }
}
