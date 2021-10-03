package sample;


import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import sample.DataHandler.SqliteHandler;
import sample.MainApp.MainApp;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MainController implements Initializable {


    @FXML public TextField UsernameField;
    @FXML public javafx.scene.control.PasswordField PasswordField;
    public Label Message;



    @FXML
    public void handle() {
        Platform.exit();
        return;
    }

    @FXML
    public void about(ActionEvent actionEvent) {


    }

    public void handleSubmitButtonAction(ActionEvent actionEvent) {

        try {
            URL url = new URL("http://www.google.com");
            URLConnection connection = url.openConnection();
            //connection.connect();
            if (UsernameField.getText().equals("admin") && PasswordField.getText().equals("admin"))
            {

                Message.setStyle("-fx-text-fill: black;");
                Message.setText("Connecting...");
                MainApp app = new MainApp();
                app.show();
                Stage s = (Stage) Message.getScene().getWindow();
                s.hide();

                app.setOnCloseRequest(new EventHandler<WindowEvent>() {
                    public void handle(WindowEvent we) {
                        Platform.exit();

                    }
                });


            }
            else
            {

                Message.setStyle("-fx-text-fill: red;");
                Message.setText("Error,check username or password");
            }
            Message.setVisible(true);

        } catch (IOException e) {
            Message.setText("No access to internet");
            Message.setVisible(true);
        }


    }

    public void handlekey(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER)
        handleSubmitButtonAction(null);
    }

    private void init()
    {

        try {
            SqliteHandler sqlh = new SqliteHandler();

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //check sql database
        init();


    }
}
