package sample.Custom;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import sample.DataModels.CarDataInfo;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;


public class CTC_Info<T> extends TableCell<T, CarDataInfo> {

    private final VBox content;
    private final Label Tel;
    private final Label owner_name;
    private final Button link;
    private  String linkurl;

    public CTC_Info() {
        super();

        link = new Button();
        link.setPrefWidth(Double.MAX_VALUE);

        Tel = new Label();
        owner_name = new Label();
        VBox vBox = new VBox(Tel, owner_name,link);

        vBox.setAlignment(Pos.CENTER_LEFT);
        content = new VBox(vBox);
        VBox.setVgrow(vBox, Priority.ALWAYS);
        content.setAlignment(Pos.CENTER_LEFT);
        content.setSpacing(10);
        link.setText("View on WebBrowser");


    }

    @Override
    protected void updateItem(CarDataInfo item, boolean empty) {
        super.updateItem(item, empty);
        if (item != null && !empty) { // <== NECESSÁRIO SENÃO CRIA VARIOS ITENS
            Tel.setText(item.getTel()); //Set de atributos
            owner_name.setText(item.getOwner());
            linkurl = item.getLink();
            link.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    try {
                        Desktop.getDesktop().browse(new URL(item.getLink()).toURI());
                    } catch (IOException | URISyntaxException e) {
                        e.printStackTrace();
                    }


                }
            });
            setGraphic(content); //Onde é exibido a caixa mesmo
        } else {
            setGraphic(null);
        }
    }



}
