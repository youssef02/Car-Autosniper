package sample.DataModels;

import java.net.URL;
import java.util.*;

/**
 * Config is used for defining the configuration of
 * each website data scraping
 * inluding
 * config name
 * website name and url
 * and data scraping algorithm
 */

public class Config {

    private String name;
    private URL Domain;
    private Boolean requireLogin = false;
    private final Boolean SearchBylink = false;//need to generate some link usefull when website use GET requests,
    //if confused go see ebay url on a search (Need of link generation element aka pattern)
    private final Boolean SearchByelement  = false;// usefull when search by link not available
    // by element you use selinum to fill the form HTML and submit (Bot)


    //base url for base search

    private String  basesearchurl ;//= "https://www.ebay-kleinanzeigen.de/s-autos/c216"
    private String separatorkey ; //+ from ebay
    private String minmaxseperatorkey ;//,  from ebay
    private String keyvalueseparator ;//:  from ebay
    private Boolean firstseparatorkeyonbase;


    public List<ConfigSearchElement> getSearchelement() {
        return Searchelement;
    }

    /*
    *Generate link from variable element
     */

    public void setkeys(String separator,Boolean firstonbase, String minmaxseparator,String keyvalueseparator){
        this.separatorkey = separator;
        this.minmaxseperatorkey = minmaxseparator;
        this.keyvalueseparator = keyvalueseparator;
        this.firstseparatorkeyonbase = firstonbase;
    }
    public String GenerateSearchlink(FilterOption fo)
    {

        //base url +( separatorkey + searchelement + keyvalue+ filter value +
        //
        // or string value / max min value
        //
        // ()+separatorkey) repeat as nessassry

        String FinalSearchlink = "";

        //add base url
         FinalSearchlink += this.basesearchurl;
         //
        if (firstseparatorkeyonbase)
        {
            FinalSearchlink += separatorkey;
        }
        // filter option to url
        //
        int cnt = 0;
        for (FilterOption.FilterOptionElement foe : fo.getElements())
        {
            for (ConfigSearchElement cse : Searchelement )
            {
                if (foe.getName().equals(cse.getName()))//if the flter eleemnt equals the config
                {
                    //check last or not to add the last separator or not
                    cnt ++;
                    if (!(cnt == fo.getElements().size()+1 || cnt == 1))
                        FinalSearchlink += separatorkey;
                    FinalSearchlink += cse.getEquivalent() + this.keyvalueseparator;
                    //if min max
                    if (foe.IsMinMax())
                    {
                        String [] minmax = foe.getValue();
                        FinalSearchlink += minmax[0]+minmaxseperatorkey+ minmax[1];
                    }
                    else if (foe.Issinglevalue())
                    {
                        FinalSearchlink += foe.getValue()[0];
                    }

                }
            }
        }
        Searchlink = FinalSearchlink;
        return FinalSearchlink;


    }

    public void setSearchelement(List<ConfigSearchElement> searchelement) {
        Searchelement = searchelement;
    }

    //here all the search elements needed to do a search from the website.
    private List<ConfigSearchElement> Searchelement = new ArrayList<ConfigSearchElement>();


    public String[] getRequiredElements() {
        return RequiredElements;
    }

    //todo: change to get the list (RequiredElements) from config elements (Done)

    /* todo: add the following cars to brand (needed for "Filter form") not in PoC
Alfa Romeo
Audi
BMW
Chevrolet
Chrysler
Citroen
Dacia
Daewoo
Daihatsu
Fiat
Ford
Honda
Hyundai
Jaguar
Jeep
Kia
Lada
Lancia
Land Rover
Lexus
Mazda
Mercedes Benz
Mini
Mitsubishi
Nissan
Opel
Peugeot
Porsche
Renault
Rover
Saab
Seat
Skoda
Smart
Subaru
Suzuki
Tesla
Toyota
Trabant
Volkswagen
Volvo
     */

    String[] RequiredElements = {"Title", "Price", "Address","Contact","Brand"};
    //todo:convert string var to element on a list (Working on.... it)
    //type:
    // Element (String name , string var ) where elements of the item can be found

    private final List<ConfigElement> Elements;

    public String[] getElementstrlst(){
        List<String> list =new  ArrayList<String>();
        for (int i = 0; i < Elements.size() ; i++) {
            list.add(Elements.get(i).getName());

        }
        return list.toArray(new String[0]);

    }

    public Config() {
        Elements = new ArrayList<ConfigElement>();
        firstseparatorkeyonbase = true;
    }

    public Config(String name) {
        this.name = name;

        Elements = new ArrayList<ConfigElement>();
        firstseparatorkeyonbase = true;


    }
    public Boolean isRequired(String name)
    {
        for (String na: RequiredElements
             ) {
            if (na.equals(name))
                return true;
        }
        return false;
    }
    public ConfigElement getElement(String name)
    {
        for (ConfigElement  e : Elements)
        {
            if (e.name.equals(name))
                return e;
        }
        return null;
    }

    public void setElement(ConfigElement e){

        for (ConfigElement se : Elements)
        {
            if (se.name == e.name)
            {
                se.setValue(e.getValue());
                return;//if found then return
            }

        }
        //if not found then this part will be excuted
        ConfigElement NewCE = new ConfigElement(e.name,e.value);
        Elements.add(NewCE);//Element added

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public URL getDomain() {
        return Domain;
    }

    public void setDomain(URL domain) {
        Domain = domain;
    }

    public Boolean getRequireLogin() {
        return requireLogin;
    }

    public void setRequireLogin(Boolean requireLogin) {
        this.requireLogin = requireLogin;
    }

    public void setBasesearchurl(String basesearchurl) {
        this.basesearchurl = basesearchurl;
    }

    public void setSearchlink(String finallink) {
        Searchlink = finallink;
    }

    public String getSearchlink() {
        return Searchlink;
    }

    private String Searchlink;
    public static class ConfigElement {
        private String name;
        private String value;

        public void setName(String name) {
            this.name = name;
        }

        public void setValue(String value) {
            if (value.startsWith("/"))
            {
                isByxPath = true;
                isByid = false;
            }
            else
            {
                isByxPath = false;
                isByid = true;
            }
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public String getValue() {
            return value;
        }

        public Boolean getByid() {
            return isByid;
        }

        public Boolean getByxPath() {
            return isByxPath;
        }

        private Boolean isByid = false;
        private Boolean isByxPath = false;

        public ConfigElement() {
            this.name = "";
            this.value = "";

        }

        public ConfigElement(String name, String value) {
            this.name = name;
            setValue(value);
        }
    }
}
