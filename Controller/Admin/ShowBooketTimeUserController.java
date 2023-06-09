package Controller.Admin;

import Model.Patient_Booked;
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

public class ShowBooketTimeUserController implements Initializable {

    @FXML
    private Button btnHome;
    @FXML
    private Button btnFreeTime;
    @FXML
    private Button btnBookedTime;
    @FXML
    private StackPane mainContent;
    @FXML
    private Button buttonLogout;
    @FXML
    private Button showBookedTime;
    @FXML
    private TableView<Patient_Booked> tabel;
    @FXML
    private TableColumn<Patient_Booked, String> firstNameTabel;
    @FXML
    private TableColumn<Patient_Booked, String> lastNameTabel;
    @FXML
    private TableColumn<Patient_Booked, String> dataTable;
    @FXML
    private TableColumn<Patient_Booked, String> timeTable;
    @FXML
    private TableColumn<Patient_Booked, String> drCoummentTable;
    @FXML
    private TableColumn<Patient_Booked, String> statusTable;
    @FXML
    private Button cancelFreeTime;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        firstNameTabel.setCellValueFactory(new PropertyValueFactory("firstName"));
        lastNameTabel.setCellValueFactory(new PropertyValueFactory("lastName"));
        dataTable.setCellValueFactory(new PropertyValueFactory("appointmentDate"));
        timeTable.setCellValueFactory(new PropertyValueFactory("appointmentTime"));
        statusTable.setCellValueFactory(new PropertyValueFactory("status"));
        drCoummentTable.setCellValueFactory(new PropertyValueFactory("doctorCommnet"));
    }

    @FXML
    private void homeOnAction(ActionEvent event) {
        ViewMangeer.dashBordUser.changeSceneToHome();
    }

    @FXML
    private void freeTimeOnAction(ActionEvent event) {
        ViewMangeer.dashBordUser.changeSceneToFreeTime();
    }

    @FXML
    private void bookedTimeOnAction(ActionEvent event) {
    }

    @FXML
    private void buttonLogoutAction(ActionEvent event) throws IOException {
        ViewMangeer.closeDashBordUserPage();
        ViewMangeer.openLoginPage();
    }

    @FXML
    private void showBookedTimeAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        ObservableList<Patient_Booked> appointmentaList
                = FXCollections.observableArrayList(Patient_Booked.getAllAppointments());
        tabel.setItems(appointmentaList);
    }

    @FXML
    private void cancelFreeTimeAction(ActionEvent event) {
           if (tabel.getSelectionModel().getSelectedItem() != null) {
            Patient_Booked selectedAppointments = tabel.getSelectionModel().getSelectedItem();

            Alert deleteConfirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
            deleteConfirmAlert.setTitle("Booked Appointments delete");
            deleteConfirmAlert.setContentText("Are you sure to delete this Booked Appointments ?");
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
                    deletedSuccessAlert.setTitle("Booked Appointments deleted");
                    deletedSuccessAlert.setContentText("Booked Appointments deleted");
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
