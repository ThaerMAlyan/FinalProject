package final_project;

import View.ViewMangeer;
import javafx.application.Application;
import javafx.stage.Stage;

public class Final_Project extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        ViewMangeer.openLoginPage();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
