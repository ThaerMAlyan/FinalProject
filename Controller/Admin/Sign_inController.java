package Controller.Admin;

import Model.SingnIn;
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

public class Sign_inController implements Initializable {

    @FXML
    private TextField emailRe;
    @FXML
    private PasswordField passwordRe;
    @FXML
    private TextField tfFirstName;
    @FXML
    private TextField tfLastName;
    @FXML
    private TextField textFieldAge;
    @FXML
    private TextField textFieldPhone;
    @FXML
    private RadioButton radioUser;
    @FXML
    private ToggleGroup role;
    @FXML
    private RadioButton radioFamel;
    @FXML
    private ToggleGroup gender;
    @FXML
    private Button buttonSign;
    @FXML
    private RadioButton radioMale;
    @FXML
    private RadioButton radioAdmin;

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
    private void buttonSignAction(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {

        if (tfFirstName.getText().isEmpty() || tfLastName.getText().isEmpty() || emailRe.getText().isEmpty()
                || passwordRe.getText().isEmpty() || textFieldAge.getText().isEmpty() || textFieldPhone.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Empty Label");
            alert.setContentText("Empty Label");
            alert.showAndWait();
        } else {

            if (passwordRe.getText().length() < 4) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("This Password Less 4 Charchater");
                alert.setContentText("This Password Less 4 Charchater");
                alert.showAndWait();
            } else {
                ArrayList<SingnIn> list = SingnIn.getAll();
                int count = 0;

                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getFirstName().equals(tfFirstName.getText()) || list.get(i).getLastName().equals(tfLastName.getText())
                            || list.get(i).getPassword().equals(passwordRe.getText()) || list.get(i).getEmail().equals(emailRe.getText())) {
                        count++;
                    }
                }

                if (count > 0) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("This Data Already Exists");
                    alert.setContentText("This Data Already Exists");
                    alert.showAndWait();
                } else {
                    String firstName = tfFirstName.getText();
                    String lastName = tfLastName.getText();
                    String password = passwordRe.getText();
                    String email = emailRe.getText();
                    int age = Integer.parseInt(textFieldAge.getText());
                    String phone = textFieldPhone.getText();
                    String gender1 = ((RadioButton) gender.getSelectedToggle()).getText();
                    String role1 = ((RadioButton) role.getSelectedToggle()).getText();
                    SingnIn signIn = new SingnIn(firstName, lastName, password, email, age, phone, gender1, role1);

                    signIn.save();

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("User inserted");
                    alert.setContentText("User inserted");
                    alert.showAndWait();

                    ViewMangeer.closeSingInPage();
                    ViewMangeer.openLoginPage();
                }
            }

        }
    }

}
