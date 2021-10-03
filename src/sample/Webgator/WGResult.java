package sample.Webgator;

import sample.DataModels.CarData;

import java.util.ArrayList;
import java.util.List;

public class WGResult {

    public List<CarData> carslist = new ArrayList<CarData>();;

    public WGResult() {

    }

    public void addCar(CarData cd)
    {
        carslist.add(cd);
    }

    public List<CarData> getCarslist()
    {
        return  carslist;
    }

    public  int count()
    {
        return carslist.size();
    }

}
