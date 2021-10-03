package sample.DataHandler;

public class CarDataHandler {


    private boolean demodata = false;

    public CarDataHandler(boolean demodata) {
        this.demodata = demodata;
    }
    public void setDemodata (boolean d)
    {
        this.demodata = d;
        if (d)
        {
            //add demo data
        }
    }

    //handle cardata from sqlite
}
