package Controller.Admin;

import Model.Patient;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.StackPane;

public class UpdatePatientController implements Initializable {

    private Patient oldPatient;

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
    private Button updatePatient;
    @FXML
    private Button deletePatient;
    @FXML
    private TextField emailRe;
    @FXML
    private PasswordField passwordRe;
    @FXML
    private TextField userNameRe;
    @FXML
    private TextField lastNameRe;
    @FXML
    private TextField textFieldAge;
    @FXML
    private TextField textFieldPhone;
    @FXML
    private RadioButton radioUser;
    @FXML
    private ToggleGroup role1;
    @FXML
    private RadioButton radioFamel;
    @FXML
    private ToggleGroup gender1;
    @FXML
    private RadioButton radioMale;
    @FXML
    private RadioButton radioAdmin;
    @FXML
    private Button showPatient;

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
    private void updatePatientAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        if (emailRe.getText().isEmpty() || passwordRe.getText().isEmpty() || userNameRe.getText().isEmpty()
                || lastNameRe.getText().isEmpty() || textFieldAge.getText().isEmpty() || textFieldPhone.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Empty Label");
            alert.setContentText("Empty Label");
            alert.showAndWait();
        } else {
            String firstName = userNameRe.getText();
            String lastName = lastNameRe.getText();
            String password = passwordRe.getText();
            String email = emailRe.getText();
            int age = Integer.parseInt(textFieldAge.getText());
            String phone = textFieldPhone.getText();
            String gender = ((RadioButton) gender1.getSelectedToggle()).getText();
            String role = ((RadioButton) role1.getSelectedToggle()).getText();
            Patient patient = new Patient(firstName, lastName, password, email, age, phone, gender, role);

            patient.setId(oldPatient.getId());

            patient.update();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Patient updated");
            alert.setContentText("Patient updated");
            alert.showAndWait();

            ViewMangeer.dashBordAdmin.changeSceneToPatient();
        }
    }

    @FXML
    private void deletePatientAction(ActionEvent event) {
        ViewMangeer.dashBordAdmin.changeSceneToPatient();
    }

    @FXML
    private void showPatientAction(ActionEvent event) {
        this.oldPatient = Controller.Admin.PatientController.patientUpdate;
        emailRe.setText(oldPatient.getEmail());
        passwordRe.setText(oldPatient.getPassword());
        userNameRe.setText(oldPatient.getFirstName());
        lastNameRe.setText(oldPatient.getLastName());
        textFieldAge.setText("" + oldPatient.getAge());
        textFieldPhone.setText(oldPatient.getPhone());

        if (oldPatient.getGender().equals("female")) {
            gender1.selectToggle(radioFamel);
        }

        if (oldPatient.getRole().equals("admin")) {
            role1.selectToggle(radioAdmin);
        }
    }

}
