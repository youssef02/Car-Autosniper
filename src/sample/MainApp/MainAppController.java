package sample.MainApp;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import sample.Custom.CTC_Description;
import sample.Custom.CTC_Info;
import sample.DataModels.*;
import sample.ErrorHandler;
import sample.Filter.FilterForm;

import java.net.URL;
import java.util.ResourceBundle;



public class MainAppController implements Initializable {




    @FXML public TableColumn image;
    @FXML public VBox Root;
    @FXML public Label f_cm;
    @FXML public Label f_f;
    @FXML public Label f_c;
    @FXML public Label f_d;
    @FXML public AnchorPane Anchorside;
    @FXML public TabPane tabPane;
    @FXML public VBox sidebox;
    @FXML public TableView FilterView;
    @FXML public Button strbtn;
    @FXML public Button delbtn;
    @FXML public Button edtbtn;


    @FXML private TableView<CarData> CarView;




    private ObservableList<CarData> data;
    private ObservableList<Filter> datafilter;
    private ObservableList<Config> dataconfig; //todo load from sql


    //todo integrer un system de filtre (doing it now)

    private static void ErrorTHrow(Exception e) {
        new ErrorHandler().ShowAlert(e);
    }

    @FXML
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //creating filter table cells. todo make data binding possible for filterview (doing)
        datafilter =  FXCollections.observableArrayList();
        data = FXCollections.observableArrayList();
        strbtn.setOnAction(e -> {
            Filter f = (Filter) FilterView.getSelectionModel().getSelectedItem();
            if (f.getStat().equals("running"))
            {
                f.Stop();
                strbtn.setText("Start");
            }
            else{
                f.Start();
                strbtn.setText("Stop");
            }



        });
        delbtn.setOnAction(e -> {
            Filter f = (Filter) FilterView.getSelectionModel().getSelectedItem();

            datafilter.remove(f);


        });

        TableColumn FilterName = new TableColumn("Filter Name");
        FilterName.setMinWidth(100);
        FilterName.setCellValueFactory(
                new PropertyValueFactory<Filter, String>("Name"));
        TableColumn FilterStat = new TableColumn("Filter Status");
        FilterStat.setMinWidth(100);
        FilterStat.setCellValueFactory(
                new PropertyValueFactory<Filter, String>("stat"));

        FilterView.getColumns().addAll(FilterName,FilterStat);
        FilterView.setItems(datafilter);
        FilterView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);



        //creating tablecell CarDataInfo

        TableColumn<CarData, CarDataInfo> cdi = new TableColumn<>("Car Owner Detail");

        cdi.setCellValueFactory(new PropertyValueFactory<>("carinfo"));

        // use custom tablecell to display info
        cdi.setCellFactory(new Callback<TableColumn<CarData, CarDataInfo>, TableCell<CarData, CarDataInfo>>() {

            @Override
            public TableCell<CarData, CarDataInfo> call(TableColumn<CarData, CarDataInfo> param) {
                return new CTC_Info<>();
            }
        });


        //creating tablecell CarDataInfo

        TableColumn<CarData, CarDataDescription> cdd = new TableColumn<>("Car Owner Detail");

        cdd.setCellValueFactory(new PropertyValueFactory<>("cardescription"));

        // use custom tablecell to display description
        cdd.setCellFactory(new Callback<TableColumn<CarData, CarDataDescription>, TableCell<CarData, CarDataDescription>>() {

            @Override
            public TableCell<CarData, CarDataDescription> call(TableColumn<CarData, CarDataDescription> param) {
                return new CTC_Description<>();
            }
        });
        CarView.getColumns().addAll(cdd,cdi);


        //handle row select

        CarView.setRowFactory(tv -> {
            TableRow<CarData> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if ( (event.getClickCount() ==1) && (! row.isEmpty()) ) {
                    System.out.print(" \n Clicked");
                    CarData r = row.getItem();
                    System.out.print(r.getCardescription().getCarmodel());
                    f_cm.setText(r.getCardescription().getCarmodel());
                    f_c.setText(r.getCardescription().getExteriorColor());
                    //CarView.getItems().remove(row.getItem());

                }
            });
            return row ;
        });


        image.setCellValueFactory(new PropertyValueFactory<CarData, ImageView>("carimg"));

        image.setPrefWidth(200.0d);


        /*buttonColumn.setCellFactory(col -> new TableCell<CarData, Void>() {

            private final VBox container;

            {
                Button add = new Button("Add to watchlist");
                Button view = new Button("View on browser");
                add.setPrefWidth(Double.MAX_VALUE);
                view.setPrefWidth(Double.MAX_VALUE);
                getTableView();



                view.setOnAction(evt -> {
                    // call some method with the row item as parameter

                    try {
                        Desktop.getDesktop().browse(new URI("http://www.google.com"));

                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    }

                });



                container = new VBox(5, add, view);
                container.setPrefWidth(200.0);
            }

            @Override
            public void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : container);
            }
        });

        CarView.getColumns().add(buttonColumn);*/
       // info.setCellValueFactory(new PropertyValueFactory<>("info"));
       /* info.setCellFactory(new Callback<TableColumn<Item, CarData>, TableCell<Item, CarData>>() {

            @Override
            public TableCell<Item, CarData> call(TableColumn<Item, CarData> param) {
                return new InfoVBox<>("");
            }
        });*/


        CarView.setItems(data);
        CarView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);



    }

    public void quit(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void NewFilter(ActionEvent actionEvent) {

        try
        {
            FilterForm nf = new FilterForm();
            Filter f =  nf.showAndReturn();
            if (f == null)
            {
                System.out.println("Returned null filter");
                return;
            }

            else
            {
                f.getData().addListener(new ListChangeListener<CarData>() {
                    @Override
                    public void onChanged(Change<? extends CarData> change) {
                        while (change.next())
                        {
                            if (change.wasAdded())
                            {
                                data.addAll(change.getAddedSubList());
                            }
                            System.out.println(change.getAddedSubList());
                        }
                    }
                });
                datafilter.add(f);  //add returned data (Filter) to filter list
                addtoviewandbind();
                if (f.getS_start())
                    f.Start();
                System.out.println("Returned valid filter");
            }

        }
        catch (Exception e)
        {
            ErrorTHrow(e);
        }

    }

    private void addtoviewandbind() {


    }

    public void DatabaseBtn(ActionEvent actionEvent) {
        System.out.println("CarDAta List Size" + data.size());

    }

    public void toggleside(ActionEvent actionEvent) {
        tabPane.setVisible(!tabPane.isVisible());
        if(!tabPane.isVisible())
        {
            Anchorside.setMaxWidth(5.0d);
            sidebox.setMaxWidth(5.0d);
        }
        else
        {
            Anchorside.setMaxWidth(Double.MAX_VALUE);
            sidebox.setMaxWidth(Double.MAX_VALUE);

        }

    }
}
