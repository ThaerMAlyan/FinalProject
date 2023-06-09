package Controller.Admin;

import Model.Appointments;
import Model.Booked_Appointments;
import Model.Patient;
import View.ViewMangeer;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class HomeController implements Initializable {

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
    private Text tfCountPatient;
    @FXML
    private Text tfBookedTime;
    @FXML
    private Text tfFreeTime;

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
    private void homeOnAction(ActionEvent event) throws SQLException {

        Patient patient = new Patient();
        int countPatient = patient.countPatient();
        tfCountPatient.setText("" + countPatient);

        Appointments appointment = new Appointments();
        int countAppointments = appointment.countFreeAppointment();
        tfFreeTime.setText("" + countAppointments);

        Booked_Appointments bookedAppointment = new Booked_Appointments();
        int countBookedAppoint = bookedAppointment.countBookedAppointment();
        tfBookedTime.setText("" + countBookedAppoint);
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

}
