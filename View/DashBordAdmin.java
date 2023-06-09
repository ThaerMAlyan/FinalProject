package View;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DashBordAdmin extends Stage {

    private final Scene home;
    private final Scene ptient;
    private final Scene addPtient;
    private final Scene updatePtient;
    private final Scene bookedTime;
    private final Scene freeTime;
    private final Scene updateFreeTime;
    private final Scene addFreeTime;

    public DashBordAdmin() throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ViewFXML/Home.fxml"));
        Parent root = loader.load();
        home = new Scene(root);

        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("/ViewFXML/Patient.fxml"));
        Parent root2 = loader2.load();
        ptient = new Scene(root2);

        FXMLLoader loader3 = new FXMLLoader(getClass().getResource("/ViewFXML/AddPatient.fxml"));
        Parent root3 = loader3.load();
        addPtient = new Scene(root3);

        FXMLLoader loader4 = new FXMLLoader(getClass().getResource("/ViewFXML/UpdatePatient.fxml"));
        Parent root4 = loader4.load();
        updatePtient = new Scene(root4);

        FXMLLoader loader5 = new FXMLLoader(getClass().getResource("/ViewFXML/showBooketTime.fxml"));
        Parent root5 = loader5.load();
        bookedTime = new Scene(root5);

        FXMLLoader loader6 = new FXMLLoader(getClass().getResource("/ViewFXML/ShowFreeTime.fxml"));
        Parent root6 = loader6.load();
        freeTime = new Scene(root6);

        FXMLLoader loader7 = new FXMLLoader(getClass().getResource("/ViewFXML/UpdateFreeTime.fxml"));
        Parent root7 = loader7.load();
        updateFreeTime = new Scene(root7);

        FXMLLoader loader8 = new FXMLLoader(getClass().getResource("/ViewFXML/AddFreeTime.fxml"));
        Parent root8 = loader8.load();
        addFreeTime = new Scene(root8);

        this.setScene(home);
        this.setTitle("Admin Dashbord");

    }

    public void changeSceneToHome() {
        this.setScene(home);
        this.setTitle("Admin Dashbord");
    }

    public void changeSceneToPatient() {
        this.setScene(ptient);
        this.setTitle("Show Patient");
    }

    public void changeSceneToAddPatient() {
        this.setScene(addPtient);
        this.setTitle("Add Patient");
    }

    public void changeSceneToUpdatePatient() {
        this.setScene(updatePtient);
        this.setTitle("Update Patient");
    }

    public void changeSceneToShowBookedTime() {
        this.setScene(bookedTime);
        this.setTitle("Show Booked Time");
    }

    public void changeSceneToShowFreeTime() {
        this.setScene(freeTime);
        this.setTitle("Show Free Time");
    }

    public void changeSceneToUpdateFreeTime() {
        this.setScene(updateFreeTime);
        this.setTitle("update Free Time");
    }

    public void changeSceneToAddFreeTime() {
        this.setScene(addFreeTime);
        this.setTitle("Add Free Time");
    }
}
