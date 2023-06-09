package Controller.Admin;

import Model.Patient;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.StackPane;

public class AddPatientController implements Initializable {

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
    private Button updatePatient1;
    @FXML
    private Button deletePatient1;
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
    private RadioButton radioFamel;
    @FXML
    private ToggleGroup gender1;
    @FXML
    private RadioButton radioMale;

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
    private void updatePatientAction1(ActionEvent event) throws SQLException, ClassNotFoundException {
        if (userNameRe.getText().isEmpty() || lastNameRe.getText().isEmpty() || emailRe.getText().isEmpty()
                || passwordRe.getText().isEmpty() || textFieldAge.getText().isEmpty() || textFieldPhone.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Empty Label");
            alert.setContentText("Empty Label");
            alert.showAndWait();
        } else {
            ArrayList<Patient> list = Patient.getAllPatient();
            int count = 0;

            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getEmail().equals(emailRe.getText()) || list.get(i).getFirstName().equals(userNameRe.getText())
                        || list.get(i).getLastName().equals(lastNameRe.getText()) || list.get(i).getPassword().equals(passwordRe.getText())) {
                    count++;
                }
            }

            if (count > 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("This Patient Already Exists");
                alert.setContentText("This Patient Already Exists");
                alert.showAndWait();
            } else {
                String firstName = userNameRe.getText();
                String lastName = lastNameRe.getText();
                String password = passwordRe.getText();
                String email = emailRe.getText();
                int age = Integer.parseInt(textFieldAge.getText());
                String phone = textFieldPhone.getText();
                String gender = ((RadioButton) gender1.getSelectedToggle()).getText();
                String role = "user";
                Patient patient = new Patient(firstName, lastName, password, email, age, phone, gender, role);

                patient.save();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Patient inserted");
                alert.setContentText("Patient inserted");
                alert.showAndWait();

                ViewMangeer.dashBordAdmin.changeSceneToPatient();
            }

        }
    }

    @FXML
    private void deletePatientAction1(ActionEvent event) {
        ViewMangeer.dashBordAdmin.changeSceneToPatient();
    }

}
