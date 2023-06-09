package Controller.Admin;

import Model.Appointments;
import View.ViewMangeer;
import java.io.IOException;
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
import javafx.scene.layout.StackPane;

public class UpdateFreeTimeController implements Initializable {

    private Appointments oldAppointments;

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
    private Button updateFreeTime;
    @FXML
    private Button cancelFreeTime;
    @FXML
    private TextField dateTextField1;
    @FXML
    private TextField timeTextField1;
    @FXML
    private TextField dayTextField1;
    @FXML
    private RadioButton radioFree1;
    @FXML
    private ToggleGroup State1;
    @FXML
    private RadioButton radioBooked1;
    @FXML
    private Button showData;

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
    private void updateFreeTimeAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        if (dateTextField1.getText().isEmpty() || dayTextField1.getText().isEmpty() || timeTextField1.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Empty Label");
            alert.setContentText("Empty Label");
            alert.showAndWait();
        } else {
            String appointmentDate = dateTextField1.getText();
            String appointmentDay = dayTextField1.getText();
            String appointmentTime = timeTextField1.getText();
            String state = ((RadioButton) State1.getSelectedToggle()).getText();

            Appointments appointments = new Appointments(appointmentDate, appointmentDay, appointmentTime, state);

            appointments.setId(oldAppointments.getId());

            appointments.update();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Appointments updated");
            alert.setContentText("Appointments updated");
            alert.showAndWait();

            ViewMangeer.dashBordAdmin.changeSceneToShowFreeTime();
        }

    }

    @FXML
    private void cancelFreeTimeAction(ActionEvent event) {
        ViewMangeer.dashBordAdmin.changeSceneToShowFreeTime();
    }

    @FXML
    private void showDataAction(ActionEvent event) {
        this.oldAppointments = Controller.Admin.ShowFreeTimeController.selectedAppointmentToUpdate;
        dateTextField1.setText(oldAppointments.getAppointmentDate());
        dayTextField1.setText(oldAppointments.getAppointmentDay());
        timeTextField1.setText(oldAppointments.getAppointmentTime());

        if (oldAppointments.getState().equals("booked")) {
            State1.selectToggle(radioBooked1);
        }
    }

}
