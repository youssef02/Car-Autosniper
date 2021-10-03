package sample.DataModels;

public class CarDataDescription {
    private String Title;
    private String Carmodel;
    private String ExteriorColor;
    private String Millage;
    private String FuelType;

    //todo update the data (Look at the filter Form)

    public String getCarmodel() {
        return Carmodel;
    }

    public void setCarmodel(String carmodel) {
        Carmodel = carmodel;
    }

    public String getExteriorColor() {
        return ExteriorColor;
    }

    public void setExteriorColor(String exteriorColor) {
        ExteriorColor = exteriorColor;
    }

    public String getMillage() {
        return Millage;
    }

    public void setMillage(String millage) {
        Millage = millage;
    }

    public String getFuelType() {
        return FuelType;
    }

    public void setFuelType(String fuelType) {
        FuelType = fuelType;
    }

    public CarDataDescription(String title, String carmodel, String color, String millage, String fueltype ) {
        Title = title;
        Carmodel = carmodel;
        ExteriorColor = color;
        Millage = millage;
        FuelType = fueltype;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }
}
