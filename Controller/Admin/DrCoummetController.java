package Controller.Admin;

import Model.Booked_Appointments;
import View.ViewMangeer;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class DrCoummetController implements Initializable {

    @FXML
    private Button saveCuoment;
    @FXML
    private TextField tfDrCoumment;
    @FXML
    private RadioButton radioWaiting;
    @FXML
    private ToggleGroup state;
    @FXML
    private RadioButton radioFinished;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void saveCuomentAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        if (tfDrCoumment.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Empty Label");
            alert.setContentText("Empty Label");
            alert.showAndWait();
        } else {
            
            int id = Controller.Admin.ShowBooketTimeController.userId;
            String states = ((RadioButton) state.getSelectedToggle()).getText();
            String drcoumment = tfDrCoumment.getText();
           
            Booked_Appointments bookedAppointment = new Booked_Appointments(states,drcoumment);
            
            bookedAppointment.setId(id);
            
            bookedAppointment.update();
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Save Coumment Successfully");
            alert.setContentText("Save Coumment Successfully");
            alert.showAndWait();
            ViewMangeer.closeDrCoumment();
        }
    }

}
