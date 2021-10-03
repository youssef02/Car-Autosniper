package sample.Custom;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import sample.DataModels.CarDataDescription;

public class CTC_Description<T> extends TableCell<T, CarDataDescription> {

    private final VBox content;
    private final Label title;
    private final Label Carmodel;
    private final Label CColor;
    private final Label Manufacturer;
    private final Label EngineType;



    public CTC_Description( ) {
        super();
        //init
        BackgroundFill bgflabel = new BackgroundFill(Color.rgb(255, 0, 80, 0.7), new CornerRadii(5.0), new Insets(-5.0));

        title = new Label();
        content  = new VBox();
        Carmodel = new Label();
        CColor = new Label();
        Manufacturer = new Label();
        EngineType = new Label();
        Carmodel.setBackground(new Background(bgflabel));
        CColor.setBackground(new Background(bgflabel));
        Manufacturer.setBackground(new Background(bgflabel));
        EngineType.setBackground(new Background(bgflabel));

        VBox titlebox = new VBox(title);
        title.setFont(Font.font ("Verdana", FontWeight.BOLD,14));
        titlebox.setAlignment(Pos.CENTER_LEFT);
        //Carmodel.setAlignment(Pos.CENTER_LEFT);
        //Color.setAlignment(Pos.CENTER_LEFT);
        HBox h1 = new HBox();
        h1.setSpacing(10d);

        HBox.setHgrow(Carmodel,Priority.ALWAYS);
        Carmodel.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(CColor,Priority.ALWAYS);
        CColor.setMaxWidth(Double.MAX_VALUE);
        h1.getChildren().add(Carmodel);
        h1.getChildren().add(CColor);


        HBox h2 = new HBox();
        h2.setSpacing(10d);

        Manufacturer.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(Manufacturer,Priority.ALWAYS);
        EngineType.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(EngineType,Priority.ALWAYS);

        h2.getChildren().add(Manufacturer);
        h2.getChildren().add(EngineType);


        content.setSpacing(12.0d);

        //addding boxes to Root main cell
        content.getChildren().add(titlebox);
        content.getChildren().add(h1);
        content.getChildren().add(h2);



        content.setPadding(new Insets(20,5,5,5));

        // setting up spacing and alignement







    }
    @Override
    protected void updateItem(CarDataDescription item, boolean empty) {
        super.updateItem(item, empty);
        if (item != null && !empty) { // <== NECESSÁRIO SENÃO CRIA VARIOS ITENS
            title.setText(item.getTitle());
            CColor.setText(item.getExteriorColor()); //Set de atributos
            Carmodel.setText(item.getCarmodel());
            Manufacturer.setText(item.getMillage()); //Set de atributos
            EngineType.setText(item.getFuelType());

            setGraphic(content); //Onde é exibido a caixa mesmo
        } else {
            setGraphic(null);
        }
    }
}
