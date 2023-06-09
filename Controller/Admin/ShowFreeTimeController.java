package Controller.Admin;

import Model.Appointments;
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

public class ShowFreeTimeController implements Initializable {

    public static Appointments selectedAppointmentToUpdate;

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
    private Button createFreeTime;
    @FXML
    private Button showFreeTime;
    @FXML
    private Button updateFreeTime;
    @FXML
    private Button deleteFreeTime;
    @FXML
    private TableView<Appointments> table;
    @FXML
    private TableColumn<Appointments, String> appointmentDateTable;
    @FXML
    private TableColumn<Appointments, String> appointmentTimeTable;
    @FXML
    private TableColumn<Appointments, String> stateTabel;
    @FXML
    private TableColumn<Appointments, String> appointmentDayTable;
    @FXML
    private TableColumn<Appointments, Integer> idTable;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        idTable.setCellValueFactory(new PropertyValueFactory("id"));
        appointmentDateTable.setCellValueFactory(new PropertyValueFactory("appointmentDate"));
        appointmentDayTable.setCellValueFactory(new PropertyValueFactory("appointmentDay"));
        appointmentTimeTable.setCellValueFactory(new PropertyValueFactory("appointmentTime"));
        stateTabel.setCellValueFactory(new PropertyValueFactory("state"));
    }

    @FXML
    private void homeOnAction(ActionEvent event) {
        ViewMangeer.dashBordAdmin.changeSceneToHome();
    }

    @FXML
    private void freeTimeOnAction(ActionEvent event) {
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
    private void createFreeTimeAction(ActionEvent event) {
        ViewMangeer.dashBordAdmin.changeSceneToAddFreeTime();
    }

    @FXML
    private void showFreeTimeAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        ObservableList<Appointments> appointmentaList
                = FXCollections.observableArrayList(Appointments.getAllAppointments());
        table.setItems(appointmentaList);
    }

    @FXML
    private void updateFreeTimeAction(ActionEvent event) throws SQLException {
        if (table.getSelectionModel().getSelectedItem() != null) {

            selectedAppointmentToUpdate = table.getSelectionModel().getSelectedItem();
            ViewMangeer.dashBordAdmin.changeSceneToUpdateFreeTime();
        } else {
            Alert warnAlert = new Alert(Alert.AlertType.WARNING);
            warnAlert.setTitle("Select an appointments");
            warnAlert.setContentText("Please select an appointments from the table view");
            warnAlert.show();
        }
    }

    @FXML
    private void deleteFreeTimeAction(ActionEvent event) throws SQLException {
        if (table.getSelectionModel().getSelectedItem() != null) {
            Appointments selectedAppointments = table.getSelectionModel().getSelectedItem();

            Alert deleteConfirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
            deleteConfirmAlert.setTitle("Appointments delete");
            deleteConfirmAlert.setContentText("Are you sure to delete this Appointments ?");
            deleteConfirmAlert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    try {
                        selectedAppointments.delete();
                    } catch (SQLException ex) {
                        Logger.getLogger(ShowFreeTimeController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(ShowFreeTimeController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Alert deletedSuccessAlert = new Alert(Alert.AlertType.INFORMATION);
                    deletedSuccessAlert.setTitle("Appointments deleted");
                    deletedSuccessAlert.setContentText("Appointments deleted");
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
