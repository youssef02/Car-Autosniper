package sample.DataModels;

import java.util.ArrayList;
import java.util.List;

public class SEOption {

    //Gonna be like BMW or Tesla see ebay "brand" option on search
    private final List<SEOptionElement> liistelement;


    public SEOption() {
        liistelement =  new ArrayList<SEOptionElement>();
    }

    public void addelement(String element)
    {
        liistelement.add(new SEOptionElement(element));
    }


    private class SEOptionElement {
         private String OptionElement = "";


        public SEOptionElement(String optionElement) {
            OptionElement = optionElement;
        }

        public String getOptionElement() {
            return OptionElement;
        }

        public void setOptionElement(String optionElement) {
            OptionElement = optionElement;
        }
    }
}
