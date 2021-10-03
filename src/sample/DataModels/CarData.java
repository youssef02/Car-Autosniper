package sample.DataModels;


import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.ImageView;

public class CarData {
    public String getUID() {
        return UID;
    }

    private final String UID;
    private final ObjectProperty<CarDataInfo> carinfo;
    private final ObjectProperty<CarDataDescription> cardescription;
    private ImageView Carimg;






    public CarDataInfo getCarinfo() {
        return carinfo.get();
    }

    public void setCarinfo(CarDataInfo carinfo) {
        this.carinfo.set(carinfo);
    }

    public CarDataDescription getCardescription() {
        return cardescription.get();
    }

    public void setCardescription(CarDataDescription cardescription) {
        this.cardescription.set(cardescription);
    }

    public ImageView getCarimg() {
        return Carimg;
    }

    public void setCarimg(ImageView carimg) {
        Carimg =  carimg;
        carimg.setFitWidth(200.0d);
    }

    public final ObjectProperty<CarDataInfo> carinfoProperty() {
        return this.carinfo;
    }
    public ObjectProperty<CarDataDescription> cardescriptionProperty() {
        return cardescription;
    }



    public CarData(String UID,CarDataInfo cdi,CarDataDescription cdd,ImageView carimg) {
        this.UID = UID;
        carinfo= new SimpleObjectProperty<>(cdi);
        cardescription = new SimpleObjectProperty<>(cdd);
        Carimg = carimg;
        Carimg.setFitWidth(200);
        Carimg.setFitHeight(100);


    }



}
