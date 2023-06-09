package View;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DashBordUser extends Stage {

    private final Scene home;
    private final Scene freeTime;
    private final Scene bookedTime;

    public DashBordUser() throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ViewFXML/Home_user.fxml"));
        Parent root = loader.load();
        home = new Scene(root);

        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("/ViewFXML/ShowFreeTimeUser.fxml"));
        Parent root2 = loader2.load();
        freeTime = new Scene(root2);

        FXMLLoader loader3 = new FXMLLoader(getClass().getResource("/ViewFXML/showBooketTimeUser.fxml"));
        Parent root3 = loader3.load();
        bookedTime = new Scene(root3);

        this.setScene(home);
        this.setTitle("User Dashbord");

    }

    public void changeSceneToHome() {
        this.setScene(home);
        this.setTitle("User Dashbord");
    }

    public void changeSceneToFreeTime() {
        this.setScene(freeTime);
        this.setTitle("Free Time");
    }

    public void changeSceneToBookedTime() {
        this.setScene(bookedTime);
        this.setTitle("Booked Time");
    }

}
