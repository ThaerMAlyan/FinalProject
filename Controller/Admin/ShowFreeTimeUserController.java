package Controller.Admin;

import Model.Appointments;
import Model.Booked_Appointments;
import View.ViewMangeer;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
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

public class ShowFreeTimeUserController implements Initializable {

    private Appointments appointmentSelected;

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
    private Button showFreeTime;
    @FXML
    private Button addAppointment;
    @FXML
    private TableView<Appointments> table;
    @FXML
    private TableColumn<Appointments, String> appointmentDataTabel;
    @FXML
    private TableColumn<Appointments, String> appointmentDayTabel;
    @FXML
    private TableColumn<Appointments, String> appointmentTimeTabel;
    @FXML
    private TableColumn<Appointments, String> statusTabel;
    @FXML
    private TableColumn<Appointments, Integer> idTabel;

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
        appointmentDataTabel.setCellValueFactory(new PropertyValueFactory("appointmentDate"));
        appointmentDayTabel.setCellValueFactory(new PropertyValueFactory("appointmentDay"));
        appointmentTimeTabel.setCellValueFactory(new PropertyValueFactory("appointmentTime"));
        statusTabel.setCellValueFactory(new PropertyValueFactory("state"));
    }

    @FXML
    private void homeOnAction(ActionEvent event) {
        ViewMangeer.dashBordUser.changeSceneToHome();
    }

    @FXML
    private void freeTimeOnAction(ActionEvent event) {
    }

    @FXML
    private void bookedTimeOnAction(ActionEvent event) {
        ViewMangeer.dashBordUser.changeSceneToBookedTime();
    }

    @FXML
    private void buttonLogoutAction(ActionEvent event) throws IOException {
        ViewMangeer.closeDashBordUserPage();
        ViewMangeer.openLoginPage();
    }

    @FXML
    private void showFreeTimeAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        ObservableList<Appointments> appointmentaList
                = FXCollections.observableArrayList(Appointments.getAllFreeAppointments());
        table.setItems(appointmentaList);
    }

    @FXML
    private void addAppointmentAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        if (table.getSelectionModel().getSelectedItem() != null) {

            ArrayList<Booked_Appointments> list = Booked_Appointments.getAllAppointments();
            int count = 0;

            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getAppointmentId() == table.getSelectionModel().getSelectedItem().getId()) {
                    count++;
                }
            }

            if (count > 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("This Date Already Booked");
                alert.setContentText("This Date Already Booked");
                alert.showAndWait();
            } else {
                int appointmentId = table.getSelectionModel().getSelectedItem().getId();
                int userId = Controller.Admin.Login_userController.idUsrer;
                String status = "Waiting";
                String doctorCommnet = "No Comment";

                Booked_Appointments bookedAppoint = new Booked_Appointments(appointmentId, userId, status, doctorCommnet);

                bookedAppoint.save();

                Alert warnAlert = new Alert(Alert.AlertType.INFORMATION);
                warnAlert.setTitle("Appointments inserted");
                warnAlert.setContentText("Appointments inserted");
                warnAlert.show();
            }
        } else {
            Alert warnAlert = new Alert(Alert.AlertType.WARNING);
            warnAlert.setTitle("Select an appointments");
            warnAlert.setContentText("Please select an appointments from the table view");
            warnAlert.show();
        }
    }

}
