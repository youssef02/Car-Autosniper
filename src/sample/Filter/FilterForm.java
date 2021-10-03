package sample.Filter;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.DataModels.Filter;
import sample.ErrorHandler;

import java.io.IOException;
import java.util.Objects;

public class FilterForm extends Stage {
    private Scene scene;
    private Filter fe;
    FilterFormController ffc;


    public FilterForm() {
        try {
            ffc = new FilterFormController();
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("FilterForm.fxml")));

            loader.setController(ffc);


            Parent root = loader.load();


            scene = new Scene(root);



            this.setScene(scene);
        } catch (IOException e) {
            new ErrorHandler().ShowAlert(e);

            Platform.exit();
        }
    }


    public FilterForm(Filter fe) {
        try {
            ffc = new FilterFormController();
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("FilterForm.fxml")));

            loader.setController(ffc);

            Parent root = loader.load();


            scene = new Scene(root);


            this.setScene(scene);
            if (fe != null) {

                this.fe = fe;
                ffc.fillform(fe);
            }

        } catch (IOException e) {
            new ErrorHandler().ShowAlert(e);

            Platform.exit();
        }


    }


    public Filter showAndReturn() {
        super.showAndWait();

        return ffc.getData();
    }


}
