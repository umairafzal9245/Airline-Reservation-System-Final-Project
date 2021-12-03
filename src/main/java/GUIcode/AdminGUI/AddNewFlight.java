package GUIcode.AdminGUI;

import GUIcode.HelloApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AddNewFlight implements Initializable {

    @FXML
    private DatePicker arriavldate;

    @FXML
    private ChoiceBox<String> arrivalhours;

    @FXML
    private ChoiceBox<String> arrivalminutes;

    @FXML
    private ChoiceBox<String> arrivalzone;

    @FXML
    private ChoiceBox<String> classe;

    @FXML
    private DatePicker departuredate;

    @FXML
    private ChoiceBox<String> departurehours;

    @FXML
    private ChoiceBox<String> departureminutes;

    @FXML
    private ChoiceBox<String> departurezone;

    @FXML
    private ChoiceBox<String> destination;

    @FXML
    private TextField fares;

    @FXML
    private TextField flightid;

    @FXML
    private ChoiceBox<String> flighttype;

    @FXML
    private ChoiceBox<String> origin;

    @FXML
    void AddFlight(ActionEvent event) {
        String org = origin.getSelectionModel().getSelectedItem();
        System.out.println(org);
    }
    @FXML
    void Back(ActionEvent event) {
        HelloApplication.window.setScene(AdminFunctionsController.manageflightsscene);
        HelloApplication.window.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> origin2 = FXCollections.observableArrayList();
        origin2.add("Pakistan");
        origin2.add("England");
        origin2.add("China");
        origin2.add("Australia");
        origin.setItems(origin2);
    }
}
