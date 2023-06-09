/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class Home_userController implements Initializable {

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
    private Text tfFreeTime;

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
    private void homeOnAction(ActionEvent event) throws SQLException {
        Appointments appointment = new Appointments();
        int countAppointments = appointment.countFreeAppointment();
        tfFreeTime.setText("" + countAppointments);

    }

    @FXML
    private void freeTimeOnAction(ActionEvent event) {
        ViewMangeer.dashBordUser.changeSceneToFreeTime();
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

}
