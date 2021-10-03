package sample.DataModels;

public class CarDataInfo {

    private String Tel;
    private String Owner;
    private String Link;


    public String getLink() {
        return Link;
    }

    public void setLink(String link) {
        Link = link;
    }

    public String getTel() {
        return Tel;
    }

    public void setTel(String tel) {
        Tel = tel;
    }

    public String getOwner() {
        return Owner;
    }

    public void setOwner(String owner) {
        Owner = owner;
    }

    public CarDataInfo(String tel,String owner,String link) {
        this.Tel = tel;
        this.Owner = owner;
        this.Link = link;
    }


}
