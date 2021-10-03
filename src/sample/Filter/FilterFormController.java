package sample.Filter;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import sample.Custom.CDT_Item;
import sample.DataModels.Filter;
import sample.DataModels.FilterOption;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class FilterFormController implements Initializable
{

    @FXML private TextField fname;
    @FXML private Button Save;
    @FXML private Button saveandfilter;

    @FXML private ComboBox<CDT_Item> BrandCombobox;
    @FXML private ComboBox<CDT_Item> FuelTYpeCombobox;
    @FXML private ComboBox<CDT_Item> VeHicleTYpeCombobox;
    @FXML private ComboBox<CDT_Item> DoOrNumCombobox;
    private ObservableList<CDT_Item> branditemlist;
    private ObservableList<CDT_Item> fueltypeitemlist;
    private ObservableList<CDT_Item> vehicletypeitemlist;
    private ObservableList<CDT_Item> numberofdoorsitemlist;
    @FXML private TextField millagemin;
    @FXML private TextField millagemax;
    @FXML private TextField powermin;
    @FXML private TextField powermax;
    @FXML private TextField firstyormin;
    @FXML private TextField firstyormax;

    private FilterOption fo;

    private Filter fe ;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    fe = new Filter();
    Save.setOnAction(e -> save(e));
    saveandfilter.setOnAction(e -> saveandfilter(e));
    initForm();

    }

    private void initForm() {



        //todo add filter settings
        List<CDT_Item> branditemslist = new ArrayList<CDT_Item>();//brand selection list
        List<CDT_Item> fueltypeitemslist = new ArrayList<CDT_Item>();// fueltype selection list
        List<CDT_Item> vehicletypeitemslist = new ArrayList<CDT_Item>();// fueltype selection list
        List<CDT_Item> numberofdoorsitemslist = new ArrayList<CDT_Item>();// fueltype selection list


        //setup data
            //brand
        String[][] brandelements = {

                {"","Any"},
                        {"alfa_romeo","Alfa Romeo"},
                {"audi","Audi"},
                {"bmv","BMW"},
                {"chevrolet","Chevrolet "},
                {"chrysler","Chrysler "},
                {"citroen","Citroen "},
                {"dacia","Dacia"},
                {"daewoo","Daewoo"},
                {"daihatsu","Daihatsu"},
                {"fiat","Fiat"},
                {"ford","Ford"},
                {"honda","Honda"},
                {"hyundai","Hyundai"},
                {"jaguar","Jaguar"},
                {"jeep","Jeep"},
                {"kia","Kia"},
                {"lada","Lada"},
                {"lancia","Lancia"},
                {"land_rover","Land Rover"},
                {"lexus","Lexus"},
                {"mazda","Mazda"},
                {"mercedes_benz","Mercedes Benz"},
                {"mini","Mini"},
                {"mitsubishi","Mitsubishi"},
                {"nissan","Nissan"},
                {"opel","Opel"},
                {"peugeot","Peugeot"},
                {"porsche","Porsche"},
                {"renault","Renault"},
                {"rover","Rover"},
                {"saab","Saab"},
                {"seat","Seat"},
                {"skoda","Skoda"},
                {"smart","Smart"},
                {"subaru","Subaru"},
                {"suzuki","Suzuki"},
                {"tesla","Tesla"},
                {"toyota","Toyota"},
                {"trabant","Trabant"},
                {"volkswagen","Volkswagen"},
                {"volvo","Volvo"}
                };
        for (String[] a : brandelements
             ) { branditemslist.add(new CDT_Item(a[1],a[0]));}
        //Fuel Type
        /*

        Petrol	benzin,
Diesel	diesel,
Natural gas	cng,
Autogas	lpg,
Hybrid	hybrid,
Electric	elektro
         */
String[][] fueltypeelemnts = {
        {"Any",""},
        {"Petrol","benzin"},
        {"Diesel","diesel"},
        {"Natural","gas	cng"},
        {"Autogas","lpg"},
        {"Hybrid","hybrid"},
        {"Electric","elektro"}

};
        for (String[] a : fueltypeelemnts
        ) { fueltypeitemslist.add(new CDT_Item(a[0],a[1]));}
        /*
        Small  Car  kleinwagen
Limousine  limousine
 Station wagon kombi
Convertible cabrio
SUV / Off-  Road Vehicle   SUV
Van / Bus  bus
Coupé coupe
         */

        String [][] vehicletype =
                {
                        {"Any",""},
                        {"Small  Car","smallcar"},
                        {"Limousine","limousine"},
                        {"Station wagon","stationwagon"},
                        {"Convertible","convertible"},
                        {"SUV","suv"},
                        {"Van","van"},
                        {"Coupe","coupe"},
                };

        for (String[] a : vehicletype
        ) { vehicletypeitemslist.add(new CDT_Item(a[0],a[1]));}

        String[][] numberofdoors ={
                {"Any",""},
                {"2/3","2_3"},
                {"4/5","4-5"},
                {"6/7","6-7"},
        };
        for (String[] a : numberofdoors
        ) { numberofdoorsitemslist.add(new CDT_Item(a[0],a[1]));}

        branditemlist = FXCollections.observableList(branditemslist);
        fueltypeitemlist = FXCollections.observableList(fueltypeitemslist);
        vehicletypeitemlist = FXCollections.observableList(vehicletypeitemslist);
        numberofdoorsitemlist = FXCollections.observableList(numberofdoorsitemslist);
        //add to form
        BrandCombobox.setItems(branditemlist);
        FuelTYpeCombobox.setItems(fueltypeitemlist);
        VeHicleTYpeCombobox.setItems(vehicletypeitemlist);
        DoOrNumCombobox.setItems(numberofdoorsitemlist);

        BrandCombobox.setValue(null);
        BrandCombobox.setValue(branditemlist.get(0));

        FuelTYpeCombobox.setValue(null);
        FuelTYpeCombobox.setValue(fueltypeitemlist.get(0));

        VeHicleTYpeCombobox.setValue(null);
        VeHicleTYpeCombobox.setValue(vehicletypeitemlist.get(0));

        DoOrNumCombobox.setValue(null);
        DoOrNumCombobox.setValue(numberofdoorsitemlist.get(0));

        //brand combo box
        //Setupconverter
        BrandCombobox.setConverter(new StringConverter<CDT_Item>() {
            @Override
            public String toString(CDT_Item object) {
                return object.getName();
            }

            @Override
            public CDT_Item fromString(String string) {
                for (CDT_Item i : branditemlist)//exact
                {
                    if (string.equals(i.getName()))
                        return i;
                }
                for (CDT_Item i : branditemlist)//startwith
                {
                    if (i.getName().toLowerCase().startsWith(string.toLowerCase()))
                    {
                        BrandCombobox.setValue(i);
                        return i;
                    }

                }
                return null;
            }
        });
        BrandCombobox.valueProperty().addListener(new ChangeListener<CDT_Item>() {
            @Override public void changed(ObservableValue ov, CDT_Item t, CDT_Item t1) {

                System.out.println("t  "+t.getValue());
                System.out.println("t1  "+t1.getValue());

            }
        });
        FuelTYpeCombobox.setConverter(new StringConverter<CDT_Item>() {
            @Override
            public String toString(CDT_Item object) {
                return object.getName();
            }

            @Override
            public CDT_Item fromString(String string) {
                for (CDT_Item i : fueltypeitemlist)//exact
                {
                    if (string.equals(i.getName()))
                        return i;
                }
                for (CDT_Item i : fueltypeitemlist)//startwith
                {
                    if (i.getName().toLowerCase().startsWith(string.toLowerCase()))
                    {
                        FuelTYpeCombobox.setValue(i);
                        return i;
                    }

                }
                return null;
            }
        });
        FuelTYpeCombobox.valueProperty().addListener(new ChangeListener<CDT_Item>() {
            @Override public void changed(ObservableValue ov, CDT_Item t, CDT_Item t1) {

                System.out.println("t  "+t.getValue());
                System.out.println("t1  "+t1.getValue());

            }
        });

        //vehicle type

        VeHicleTYpeCombobox.setConverter(new StringConverter<CDT_Item>() {
            @Override
            public String toString(CDT_Item object) {
                return object.getName();
            }

            @Override
            public CDT_Item fromString(String string) {
                for (CDT_Item i : vehicletypeitemlist)//exact
                {
                    if (string.equals(i.getName()))
                        return i;
                }
                for (CDT_Item i : vehicletypeitemlist)//startwith
                {
                    if (i.getName().toLowerCase().startsWith(string.toLowerCase()))
                    {
                        VeHicleTYpeCombobox.setValue(i);
                        return i;
                    }

                }
                return null;
            }
        });
        VeHicleTYpeCombobox.valueProperty().addListener(new ChangeListener<CDT_Item>() {
            @Override public void changed(ObservableValue ov, CDT_Item t, CDT_Item t1) {

                System.out.println("t  "+t.getValue());
                System.out.println("t1  "+t1.getValue());

            }
        });

        //Vehicle  number of doors
        DoOrNumCombobox.setConverter(new StringConverter<CDT_Item>() {
            @Override
            public String toString(CDT_Item object) {
                return object.getName();
            }

            @Override
            public CDT_Item fromString(String string) {
                for (CDT_Item i : numberofdoorsitemlist)//exact
                {
                    if (string.equals(i.getName()))
                        return i;
                }
                for (CDT_Item i : numberofdoorsitemlist)//startwith
                {
                    if (i.getName().toLowerCase().startsWith(string.toLowerCase()))
                    {
                        DoOrNumCombobox.setValue(i);
                        return i;
                    }

                }
                return null;
            }
        });
        DoOrNumCombobox.valueProperty().addListener(new ChangeListener<CDT_Item>() {
            @Override public void changed(ObservableValue ov, CDT_Item t, CDT_Item t1) {

                System.out.println("t  "+t.getValue());
                System.out.println("t1  "+t1.getValue());

            }
        });

    }

    //todo save return filter data to main app  form.
    public void save(ActionEvent actionEvent) {

        generateFO();

    System.out.println("generate FilterOption and return");
        Node source = (Node)  actionEvent.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }

    private void generateFO() {
        //form --> fo
        if (fname.getText().isEmpty())//check name is not empty
        {
            fname.getStyleClass().add("error");
            return;
        }
        else
        {
            fe.setName(fname.getText());
            fname.getStyleClass().remove("error");
        }

        fo = new FilterOption();

        if (!BrandCombobox.getValue().getValue().equals(""))//check brand is not empty
        {
            fo.addFilterOptionElement("brand", new String[]{BrandCombobox.getValue().getValue()});
        }
        if (!FuelTYpeCombobox.getValue().getValue().equals(""))//check brand is not empty
        {
            fo.addFilterOptionElement("fueltype", new String[]{FuelTYpeCombobox.getValue().getValue()});
        }
        if (!millagemin.getText().isEmpty() && !millagemax.getText().isEmpty())//millage min max
        {
            fo.addFilterOptionElement("millage", new String[]{millagemin.getText(),millagemax.getText()});
        }
        if (!powermin.getText().isEmpty() && !powermax.getText().isEmpty())//power
        {
            fo.addFilterOptionElement("power", new String[]{powermin.getText(),powermax.getText()});
        }
        if (!millagemin.getText().isEmpty() && !millagemax.getText().isEmpty())//firstyear
        {
            fo.addFilterOptionElement("firstyear", new String[]{firstyormin.getText(),firstyormax.getText()});
        }
        if (!VeHicleTYpeCombobox.getValue().getValue().equals(""))//check brand is not empty
        {
            fo.addFilterOptionElement("vehiculetype", new String[]{VeHicleTYpeCombobox.getValue().getValue()});
        }
        fe.setFo(fo);



    }

    public void saveandfilter(ActionEvent actionEvent) {
        generateFO();
        fe.setS_start(true);
        System.out.println(fe);

        System.out.println("generate FilterOption and return and run filter");
        Node source = (Node)  actionEvent.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public Filter getData() {

        return fe;
    }

    public void fillform(Filter fe) {
        //todo fill form from data fe
        //fo --> form
    }
    public void showallert(String text)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Message");
        alert.setHeaderText("Look, an Information Dialog");
        alert.setContentText(text);
        alert.showAndWait().ifPresent(rs -> {
            if (rs == ButtonType.OK) {
                System.out.println("Pressed OK.");
            }
        });
    }
}
