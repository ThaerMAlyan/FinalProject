package Controller.Admin;

import Model.Appointments;
import View.ViewMangeer;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.StackPane;

public class AddFreeTimeController implements Initializable {

    @FXML
    private Button btnHome;
    @FXML
    private Button btnFreeTime;
    @FXML
    private Button btnBookedTime;
    @FXML
    private Button btnPatient;
    @FXML
    private StackPane mainContent;
    @FXML
    private Button buttonLogout;
    @FXML
    private Button saveFreeTime;
    @FXML
    private Button cancelFreeTime;
    @FXML
    private TextField dateTextField;
    @FXML
    private TextField timeTextField;
    @FXML
    private TextField dayTextField;
    @FXML
    private RadioButton radioFree;
    @FXML
    private ToggleGroup State;
    @FXML
    private RadioButton radioBooked;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void homeOnAction(ActionEvent event) {
        ViewMangeer.dashBordAdmin.changeSceneToHome();
    }

    @FXML
    private void freeTimeOnAction(ActionEvent event) {
        ViewMangeer.dashBordAdmin.changeSceneToShowFreeTime();
    }

    @FXML
    private void bookedTimeOnAction(ActionEvent event) {
        ViewMangeer.dashBordAdmin.changeSceneToShowBookedTime();
    }

    @FXML
    private void patientOnAction(ActionEvent event) {
        ViewMangeer.dashBordAdmin.changeSceneToPatient();
    }

    @FXML
    private void buttonLogoutAction(ActionEvent event) throws IOException {
        ViewMangeer.closeDashBordAdminInPage();
        ViewMangeer.openLoginPage();
        ViewMangeer.login.changeSceneToLoginAdmin();
    }

    @FXML
    private void saveFreeTimeAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        if (dateTextField.getText().isEmpty() || dayTextField.getText().isEmpty() || timeTextField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Empty Label");
            alert.setContentText("Empty Label");
            alert.showAndWait();
        } else {
            ArrayList<Appointments> list = Appointments.getAllAppointments();
            int count = 0;

            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getAppointmentDate().equals(dateTextField.getText()) && list.get(i).getAppointmentDay().equals(dayTextField.getText())
                        && list.get(i).getAppointmentTime().equals(timeTextField.getText())) {
                    count++;
                }
            }

            if (count > 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("This Date Already Exists");
                alert.setContentText("This Date Already Exists");
                alert.showAndWait();
            } else {

                String appointmentDate = dateTextField.getText();
                String appointmentDay = dayTextField.getText();
                String appointmentTime = timeTextField.getText();
                String state = ((RadioButton) State.getSelectedToggle()).getText();
                Appointments appointments = new Appointments(appointmentDate, appointmentDay, appointmentTime, state);

                appointments.save();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Appointments inserted");
                alert.setContentText("Appointments inserted");
                alert.showAndWait();

                ViewMangeer.dashBordAdmin.changeSceneToShowFreeTime();

            }

        }
    }

    @FXML
    private void cancelFreeTimeAction(ActionEvent event) {
        ViewMangeer.dashBordAdmin.changeSceneToShowFreeTime();
    }

}
