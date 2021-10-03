package sample.Webgator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import sample.DataModels.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

class WebGatorTest {

    WebGator wg;
    Config C = new Config("Ebay");
    FilterOption fo;
    Filter f = new Filter(fo);

    WebGatorTest() throws MalformedURLException {
    }

    @org.junit.jupiter.api.BeforeEach
    void setUp() {

        //setup filter
        //todo filter need to setup (working on).

        f.setName("Test Webgator ebay");
        f.setDescription("Test");
        //filter option
        fo = new FilterOption();
        //brand
        fo.addFilterOptionElement("brand", new String[]{"bmw"});
        //millage
        fo.addFilterOptionElement("millage", new String[]{"4500", "150000"});
        f.setFo(fo);

        //setup elements for config
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
        //todo add search support (Currently working on)
        getsearchelemnt();


    }

    @Test
    void Genlink() {
        System.out.println("Generating link");
        String finallink = C.GenerateSearchlink(fo);
        System.out.println(finallink);
    }

    private void getsearchelemnt() {
        //setup code config search element
        //autos.marke_s:
        //https://www.ebay-kleinanzeigen.de/s-autos/audi/c216+autos.marke_s:audi
        String[] searchelement = {"brand", "fueltype", "millage", "power","firstyear","vehiculetype","doornum"};// example brand/ price
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
        String[] equivalant = {"autos.marke_s", "autos.fuel_s", "autos.km_i", "autos.power_i:","autos.ez_i","autos.typ_s","autos.anzahl_tueren_s"}; // equivalant &brand=AUDI/ min= 500
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


        C.setSearchelement(searchElementList);


    }


    @Test
    void getConf() {

        Assertions.assertEquals(1, 1);
    }

    @Test
    void getWebDriver() {
    }

    @Test
    void getData() throws IOException {
        //creating new webgator from config
        wg = new WebGator(C, wg.f);
        //        webDriver.get(c.getDomain().toString());
        String url = "https://www.ebay-kleinanzeigen.de/s-anzeige/mini-one-r56-brick-lane/1822039098-216-19115";
        String imgurl = "https://i.ebayimg.com/00/s/MTEwMlg4MjY=/z/tVwAAOSwTTFg8MC0/$_59.JPG";
        String uid = "!@#$%^";
        CarData a = wg.getCarDataElement(uid,url,imgurl);

    }

    @Test
    void testSearch()
    {
        wg = new WebGator(C,f);
        String finallink = C.GenerateSearchlink(fo);
        C.setSearchlink(finallink);
        System.out.println(finallink);
        wg.executeResearchNR();

    }

    @Test
    void fulltestSearch() throws IOException {
        wg = new WebGator(C,f);
        wg.getConf().setDomain(new URL("https://www.ebay-kleinanzeigen.de"));
        wg = new WebGator(C,f);
        String finallink = C.GenerateSearchlink(fo);
        C.setSearchlink(finallink);
        System.out.println(finallink);
        WGResult result=  wg.executeResearchNRandextract();
        System.out.println("=========================result Count");
        System.out.println(result.count());


    }
}