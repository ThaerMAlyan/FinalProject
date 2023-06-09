package Controller.Admin;

import Model.Patient;
import View.ViewMangeer;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;

public class PatientController implements Initializable {

    public static Patient patientUpdate;

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
    private Button addPatient;
    @FXML
    private Button ShowPatient;
    @FXML
    private Button updatePatient;
    @FXML
    private Button deletePatient;
    @FXML
    private TableView<Patient> table;
    @FXML
    private TableColumn<Patient, Integer> idTabel;
    @FXML
    private TableColumn<Patient, String> firstTabel;
    @FXML
    private TableColumn<Patient, String> lastTabel;
    @FXML
    private TableColumn<Patient, String> emailTabel;
    @FXML
    private TableColumn<Patient, Integer> ageTabel;
    @FXML
    private TableColumn<Patient, String> ganderTabel;
    @FXML
    private TableColumn<Patient, String> phoneTabel;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        idTabel.setCellValueFactory(new PropertyValueFactory("id"));
        firstTabel.setCellValueFactory(new PropertyValueFactory("firstName"));
        lastTabel.setCellValueFactory(new PropertyValueFactory("lastName"));
        emailTabel.setCellValueFactory(new PropertyValueFactory("email"));
        ageTabel.setCellValueFactory(new PropertyValueFactory("age"));
        ganderTabel.setCellValueFactory(new PropertyValueFactory("gender"));
        phoneTabel.setCellValueFactory(new PropertyValueFactory("phone"));

    }

    @FXML
    private void homeOnAction(ActionEvent event) throws IOException {
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
    }

    @FXML
    private void buttonLogoutAction(ActionEvent event) throws IOException {
        ViewMangeer.closeDashBordAdminInPage();
        ViewMangeer.openLoginPage();
        ViewMangeer.login.changeSceneToLoginAdmin();
    }

    @FXML
    private void addPatientAction(ActionEvent event) {
        ViewMangeer.dashBordAdmin.changeSceneToAddPatient();
    }

    @FXML
    private void ShowPatientAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        ObservableList<Patient> patientList
                = FXCollections.observableArrayList(Patient.getAllPatient());
        table.setItems(patientList);
    }

    @FXML
    private void updatePatientAction(ActionEvent event) {
        if (table.getSelectionModel().getSelectedItem() != null) {

            patientUpdate = table.getSelectionModel().getSelectedItem();
            ViewMangeer.dashBordAdmin.changeSceneToUpdatePatient();
        } else {
            Alert warnAlert = new Alert(Alert.AlertType.WARNING);
            warnAlert.setTitle("Select an patient");
            warnAlert.setContentText("Please select an patient from the table view");
            warnAlert.show();
        }
    }

    @FXML
    private void deletePatientAction(ActionEvent event) {
        if (table.getSelectionModel().getSelectedItem() != null) {
            Patient selectedPatient = table.getSelectionModel().getSelectedItem();

            Alert deleteConfirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
            deleteConfirmAlert.setTitle("Patient delete");
            deleteConfirmAlert.setContentText("Are you sure to delete this Patient ?");
            deleteConfirmAlert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    try {
                        selectedPatient.delete();
                    } catch (SQLException ex) {
                        Logger.getLogger(ShowFreeTimeController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(ShowFreeTimeController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Alert deletedSuccessAlert = new Alert(Alert.AlertType.INFORMATION);
                    deletedSuccessAlert.setTitle("Patient deleted");
                    deletedSuccessAlert.setContentText("Patient deleted");
                    deletedSuccessAlert.show();
                }
            });

        } else {
            Alert warnAlert = new Alert(Alert.AlertType.WARNING);
            warnAlert.setTitle("Select an appointments");
            warnAlert.setContentText("Please select an appointments from the table view");
            warnAlert.show();
        }
    }

}
