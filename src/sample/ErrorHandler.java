package sample;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ErrorHandler  {

    public ErrorHandler() {
    }

    public void ShowAlert(Exception e){
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        String exceptionAsString = sw.toString();
        OKAlert("Error:",e.toString(),exceptionAsString,"");
    }
    private void OKAlert(String Title,String HeaderText,String ContentText,String Case){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(Title);
        alert.setHeaderText(HeaderText);
        alert.setContentText(ContentText);
        alert.showAndWait().ifPresent(rs -> {
            if (rs == ButtonType.OK) {
                switch (Case)
                {
                    case "":{

                        break;
                    }
                }

            }
        });
    }
}
