package sample.MainApp;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.ErrorHandler;

import java.io.IOException;

public class MainApp extends Stage {
    Scene scene ;
    public MainApp() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("mainapp.fxml"));
            scene = new Scene(root);



            setMinWidth(1280);
            setMinHeight(768);

            this.setScene(scene);




        } catch (IOException e)
        {
            new ErrorHandler().ShowAlert(e);

            Platform.exit();
        }


    }

}
