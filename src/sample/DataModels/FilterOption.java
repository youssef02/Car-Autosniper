package sample.DataModels;

import java.util.ArrayList;
import java.util.List;

public class FilterOption {

    public List<FilterOptionElement> getElements() {
        return elements;
    }

    private final List<FilterOptionElement> elements;

    public FilterOption() {
        elements = new ArrayList<FilterOptionElement>();
    }

    public void addFilterOptionElement(String name,String[] value){
         elements.add(new FilterOptionElement(name,value));
    }

    public void removeFilterOptionElement(String name){
        elements.removeIf(feo -> feo.getName().equals(name));
    }

    public void updateFilterOptionElement(FilterOptionElement feo,String[] value)
    {

        if (elements.contains(feo))
        {
            FilterOptionElement target = elements.get(elements.indexOf(feo));
            target.Value = value;
            elements.set(elements.indexOf(feo),target);
        }
        else
            addFilterOptionElement(feo.getName(), value);



    }

   /* private String BD;     //brand | list

    private String FT;     //fuel type | list

    private String[] MI;     //millage | minmax

    private String[] PR;     //Power | minmax

    private String[] FYoR;     //First Year of registration | minmax

    private String VT;     //vehicle type  | list

    private String NoD;     //Number of doors  | list
*/

    public class FilterOptionElement {
        private final String name;
        private String[] Value;
        private Boolean isMinMax = false;

        public String getName() {
            return name;
        }

        public String[] getValue() {
            return Value;
        }

        public Boolean IsMinMax() {
            return isMinMax;
        }

        public Boolean Issinglevalue() {
            return issinglevalue;
        }

        private Boolean issinglevalue = false;

        public FilterOptionElement(String name, String[] value) {
            this.name = name;
            if (value.length == 2)
            {
                isMinMax = true;
            }
            else
                issinglevalue = true;
            Value = value;
        }


    }
}
