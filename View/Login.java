
package View;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Login extends Stage {

    private final Scene loginAdmin;
    private final Scene loginUser;

    public Login() throws IOException {

        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/ViewFXML/login.fxml"));
        Parent root1 = loader1.load();
        loginAdmin = new Scene(root1);

        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("/ViewFXML/login_user.fxml"));
        Parent root2 = loader2.load();
        loginUser = new Scene(root2);

        this.setScene(loginUser);
        this.setTitle("Login User");

    }

    public void changeSceneToLoginAdmin() {
        this.setScene(loginAdmin);
        this.setTitle("Login Admin");
    }

    public void changeSceneToLoginUser() {
        this.setScene(loginUser);
        this.setTitle("Login User");

    }
}
