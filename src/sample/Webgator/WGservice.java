package sample.Webgator;

import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import sample.DataModels.Config;
import sample.DataModels.ConfigSearchElement;
import sample.DataModels.FilterOption;
import sample.DataModels.SEOption;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class WGservice extends ScheduledService<WGResult> {


    Config C = new Config("Ebay");//todo remove after because this belong to the test case only
    private FilterOption fo;
    private WGResult CDOP = new WGResult();
    private List<Config> configlist = new ArrayList<Config>();
    private WebGator wg;

    public WGservice() {
        System.out.println("POINT2");
    }

    public WGservice(FilterOption fo) {

        this.fo = fo;

    }

    public void SetFilterOpt(FilterOption fo) {
        System.out.println("POINT3");
        this.fo = fo;
    }

    //todo handle background repeated tasks.

    public void GetConfigs() throws MalformedURLException {

        //todo setup config from sql

        //for test sake using ebay config only
        /*
        For performance reason the app should get data directly from sql  webgator need  one point data of config list

        many wegbator use one config
         */
        getsearchelemnt();


    }

    private void getsearchelemnt() throws MalformedURLException {


        //part 1

        //setup elements
        String[] elements = {
                "Title",
                "Price",
                "Address",
                "Contact",
                "Phone",
                //1div
                "Brand",
                "Model",
                "Millage",
                "First registration",
                "Fuel Type",
                "Power",
                "Transmission",
                //2div
                "Vehicle type",
                "Number of doors",
                "HU to",
                "Environmental badge",
                "Emission class",
                "Exterior color",
                "Interior material",
                "Vehicle condition",
        };
        String[] elementsvalues = {
                "viewad-title",
                "viewad-price",
                "viewad-locality",
                "//*[@id=\"viewad-contact\"]/div/ul/li[1]/span/span[1]",
                "viewad-contact-phone",
                //1div
                "//*[@id=\"viewad-details\"]/ul/div[1]/li[contains(text(),'Marke')]/span",//brand
                "//*[@id=\"viewad-details\"]/ul/div[1]/li[contains(text(),'Modell')]/span",//model
                "//*[@id=\"viewad-details\"]/ul/div[1]/li[contains(text(),'Kilometerstand')]/span",//millage
                "//*[@id=\"viewad-details\"]/ul/div[1]/li[contains(text(),'Erstzulassung')]/span",//first registrqtion
                "//*[@id=\"viewad-details\"]/ul/div[1]/li[contains(text(),'Kraftstoffart')]/span",//Fuell type
                "//*[@id=\"viewad-details\"]/ul/div[1]/li[contains(text(),'Leistung')]/span",//power
                "//*[@id=\"viewad-details\"]/ul/div[1]/li[contains(text(),'Getriebe')]/span",//transmission
                //2div
                "//*[@id=\"viewad-details\"]/ul/div[2]/li[contains(text(),'Fahrzeugtyp')]/span",//Vehicle type
                "//*[@id=\"viewad-details\"]/ul/div[2]/li[contains(text(),'Anzahl Türen')]/span",//Number of doors
                "//*[@id=\"viewad-details\"]/ul/div[2]/li[contains(text(),'HU bis')]/span",//HU to
                "//*[@id=\"viewad-details\"]/ul/div[2]/li[contains(text(),'Umweltplakette')]/span",//Environmental badge
                "//*[@id=\"viewad-details\"]/ul/div[2]/li[contains(text(),'Schadstoffklasse')]/span",//Emission class
                "//*[@id=\"viewad-details\"]/ul/div[2]/li[contains(text(),'Außenfarbe')]/span",//Exterior color
                "//*[@id=\"viewad-details\"]/ul/div[2]/li[contains(text(),'Material Innenausstattung')]/span",//Interior material
                "//*[@id=\"viewad-details\"]/ul/div[2]/li[contains(text(),'Fahrzeugzustand')]/span",//Vehicle condition


        };

        for (int i = 0; i < elements.length; i++) {
            Config.ConfigElement element = new Config.ConfigElement(elements[i], elementsvalues[i]);
            //System.out.println(element.getValue());
            C.setElement(element);
        }

        //base url //=
        C.setBasesearchurl("https://www.ebay-kleinanzeigen.de/s-autos/c216");
        //setup code config search element


        //autos.marke_s:
        //https://www.ebay-kleinanzeigen.de/s-autos/audi/c216+autos.marke_s:audi

        //part 2
        //setup code config search element
        //autos.marke_s:
        //https://www.ebay-kleinanzeigen.de/s-autos/audi/c216+autos.marke_s:audi
        String[] searchelement = {"brand", "fueltype", "millage", "power", "firstyear", "vehiculetype", "doornum"};// example brand/ price
        String[][] searchelementoption = {
                {//brand
                        "alfa_romeo",
                        "audi",
                        "bmv",
                        "chevrolet",
                        "chrysler",
                        "citroen",
                        "dacia",
                        "daewoo",
                        "daihatsu",
                        "fiat",
                        "ford",
                        "honda",
                        "hyundai",
                        "jaguar",
                        "jeep",
                        "kia",
                        "lada",
                        "lancia",
                        "land_rover",
                        "lexus",
                        "mazda",
                        "mercedes_benz",
                        "mini",
                        "mitsubishi",
                        "nissan",
                        "opel",
                        "peugeot",
                        "porsche",
                        "renault",
                        "rover",
                        "saab",
                        "seat",
                        "skoda",
                        "smart",
                        "subaru",
                        "suzuki",
                        "tesla",
                        "toyota",
                        "trabant",
                        "volkswagen",
                        "volvo"
                }
                , {//fueltype
                "benzin",
                "diesel",
                "cng",
                "lpg",
                "hybrid",
                "elektro",
                "andere"
        }
                , {//millage
                "min:5000",
                "max:150000"
        },
                {//power
                        "min:34",
                        "max:252"
                },
                {//first year
                        "min:2000",
                        "max:2021"
                },
                {//vehicule type
                        "smallcar",
                        "limousine",
                        "stationwagon",
                        "convertible",
                        "suv",
                        "van",
                        "coupe"

                },
                {//door num
                        /*
                         {"2/3","2_3"},
                    {"4/5","4-5"},
                    {"6/7","6-7"},
                         */
                        "2_3",
                        "4_5",
                        "6_7"
                }};//value or min,max
        String[] equivalant = {"autos.marke_s", "autos.fuel_s", "autos.km_i", "autos.power_i:", "autos.ez_i", "autos.typ_s", "autos.anzahl_tueren_s"}; // equivalant &brand=AUDI/ min= 500
        C.setBasesearchurl("https://www.ebay-kleinanzeigen.de/s-autos/c216"); //setup base search url.
        C.setkeys("+", true, ",", ":");//setup key separator for ebay eample

        List<ConfigSearchElement> searchElementList = new ArrayList<ConfigSearchElement>();

        for (int i = 0; i < searchelement.length; i++)// for each search element
        {
            ConfigSearchElement con = new ConfigSearchElement(searchelement[i]);
            con.setEquivalent(equivalant[i]);
            List<SEOption> searchelementoptionList = new ArrayList<SEOption>();
            for (int j = 0; j < searchelementoption[i].length; j++) {
                SEOption seo = new SEOption();
                seo.addelement(searchelementoption[i][j]);
                searchelementoptionList.add(seo);

            }
            con.setSearchelementoption(searchelementoptionList);
            searchElementList.add(con);

        }

        C.setDomain(new URL("https://www.ebay-kleinanzeigen.de"));
        C.setSearchelement(searchElementList);
        wg.setconf(C);


    }

    public void StartTheService() {

        System.out.println("POINT4");
        if (!isRunning()) {
            reset();
            start();


        }
    }

    @Override
    protected Task<WGResult> createTask() {
        return new Task<WGResult>() {


            @Override
            protected WGResult call() throws Exception {
                wg = new WebGator();


                try {


                    wg.getConf().setDomain(new URL("https://www.ebay-kleinanzeigen.de"));

                    GetConfigs();
                    String finallink = wg.getConf().GenerateSearchlink(fo);
                    wg.getConf().setSearchlink(finallink);
                    CDOP = wg.executeResearchNRandextract();


                    return CDOP;
                } catch (Exception ex) {
                    System.out.println("Error" + ex.getMessage());
                    wg.getError();
                }

                return null;
            }
        };
    }

    public WGResult getCDOP() {
        return CDOP;
    }

    public void setCDOP(WGResult CDOP) {
        this.CDOP = CDOP;
    }


}
