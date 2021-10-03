package sample.DataModels;

import java.util.ArrayList;
import java.util.List;

/**
 * Element used for search in the webdriver, you got
 */
public class ConfigSearchElement {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name; // name of search element like for example "brand"
    private String equivalent = "";//value can arrange from URL parameters to xpath to actualt element

    //if by link
    /*
    should look like this example
       eauivalant  = "autos.marke_s"

     */


    private List<SEOption> searchelementoption = new ArrayList<SEOption>();

    public ConfigSearchElement(String name) {
        this.name = name;

    }


    public ConfigSearchElement() {
    }

    public String getEquivalent() {
        return equivalent;
    }

    public void setEquivalent(String equivalent) {
        this.equivalent = equivalent;
    }

    public List<SEOption> getSearchelementoption() {
        return searchelementoption;
    }

    public void setSearchelementoption(List<SEOption> searchelementoption) {
        this.searchelementoption = searchelementoption;
    }

}
