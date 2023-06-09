package Controller.Admin;

import Model.Patient_Booked;
import View.ViewMangeer;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;

public class ShowBooketTimeController implements Initializable {

    public static int userId;

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
    private Button showBookedTime;
    @FXML
    private Button drCoumment;
    @FXML
    private TableView<Patient_Booked> table;
    @FXML
    private TableColumn<Patient_Booked, String> firstNameTable;
    @FXML
    private TableColumn<Patient_Booked, String> lastNameTable;
    @FXML
    private TableColumn<Patient_Booked, String> dateTable;
    @FXML
    private TableColumn<Patient_Booked, String> timeTable;
    @FXML
    private TableColumn<Patient_Booked, String> stateTable;
    @FXML
    private TableColumn<Patient_Booked, Integer> idTable;
    @FXML
    private TableColumn<Patient_Booked, String> drCoummentTable;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        idTable.setCellValueFactory(new PropertyValueFactory("id"));
        firstNameTable.setCellValueFactory(new PropertyValueFactory("firstName"));
        lastNameTable.setCellValueFactory(new PropertyValueFactory("lastName"));
        dateTable.setCellValueFactory(new PropertyValueFactory("appointmentDate"));
        timeTable.setCellValueFactory(new PropertyValueFactory("appointmentTime"));
        stateTable.setCellValueFactory(new PropertyValueFactory("status"));
        drCoummentTable.setCellValueFactory(new PropertyValueFactory("doctorCommnet"));
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
    private void showBookedTimeAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        ObservableList<Patient_Booked> appointmentaList
                = FXCollections.observableArrayList(Patient_Booked.getAllAppointments());
        table.setItems(appointmentaList);
    }

    @FXML
    private void drCoummentAction(ActionEvent event) throws IOException {
        if (table.getSelectionModel().getSelectedItem() != null) {
            userId = table.getSelectionModel().getSelectedItem().getId();
            ViewMangeer.openDrCoumment();
        } else {
            Alert warnAlert = new Alert(Alert.AlertType.WARNING);
            warnAlert.setTitle("Select an appointments");
            warnAlert.setContentText("Please select an appointments from the table view");
            warnAlert.show();
        }
    }

}
