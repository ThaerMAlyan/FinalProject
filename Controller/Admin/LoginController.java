
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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController implements Initializable {

    @FXML
    private TextField tfUserName;
    @FXML
    private PasswordField password;
    @FXML
    private Label labelError;
    @FXML
    private Button loginButton;
    @FXML
    private Button signButton;
    @FXML
    private Button loginUser;

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
    private void loginButtonAction(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
          if (tfUserName.getText().isEmpty() || password.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Empty Label");
            alert.setContentText("Empty Label");
            alert.showAndWait();
        } else {
            ArrayList<SingnIn> list = SingnIn.getAllAdmin();
            int count = 0;
            String name = "";
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getEmail().equals(tfUserName.getText()) && list.get(i).getPassword().equals(password.getText())) {
                    name = list.get(i).getFirstName();
                    count++;
                }
            }
            if (count > 0) {
                ViewMangeer.closeLoginPage();
                ViewMangeer.openDashBordAdminPage();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Welcom " + name.toUpperCase());
                alert.setContentText("Welcom " + name.toUpperCase());
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Wrong Data");
                alert.setContentText("Wrong Data");
                alert.showAndWait();
            }
            
        }
    }

    @FXML
    private void signButtonAction(ActionEvent event) throws IOException {
        ViewMangeer.closeLoginPage();
        ViewMangeer.openSingInPage();
    }

    @FXML
    private void loginUserAction(ActionEvent event) {
        ViewMangeer.login.changeSceneToLoginUser();
    }

}
