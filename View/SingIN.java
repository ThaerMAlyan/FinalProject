
package View;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class SingIN extends Stage{
    

    public SingIN() throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ViewFXML/Sign_in.fxml"));
        Parent root = loader.load();
        Scene signIn = new Scene(root);
        this.setScene(signIn);
        this.setTitle("Login User");

    }

}
