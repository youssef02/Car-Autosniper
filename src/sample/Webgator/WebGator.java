package sample.Webgator;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import sample.DataModels.*;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class WebGator {

    //handle extracting data from web site
    // need config as it responsible for the website
    // data needed example ( title id tag, price id tag,
    // tel id tag)
    private Config conf;
    private final WebDriver webDriver;
    WebDriver tmp = new HtmlUnitDriver(BrowserVersion.getDefault());
    public Filter f;

    public WebGator() {

        webDriver = new HtmlUnitDriver(BrowserVersion.CHROME);
        webDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        conf = new Config();//PLaceholder
    }

    public WebGator(Config c,Filter f) {
        this.conf = c;
        this.f = f;

        webDriver = new HtmlUnitDriver(BrowserVersion.CHROME);
        webDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);


    }

    public Config getConf() {
        return conf;
    }

    public WebDriver getWebDriver() {
        return webDriver;
    }

    //on test void on production return  WGresult
    public WGResult executeResearch(){

        WGResult r = new WGResult();
        webDriver.get(conf.getSearchlink());
        WebElement list = webDriver.findElement(By.id("srchrslt-adtable"));
        //System.out.println(list.getText());//print text
        List<WebElement> elements = list.findElements(By.xpath("//*[@id=\"srchrslt-adtable\"]/li[(contains(@class,'lazyload-item') and  not( contains(@class,'is-topad')))]"));
        for (WebElement item: elements
             ) {
            System.out.println(item.getText());
            System.out.println("==========================================================");

        }
        return r;

    }
    public void executeResearchNR(){

        WGResult r = new WGResult();
        webDriver.get(conf.getSearchlink());
        WebElement list = webDriver.findElement(By.id("srchrslt-adtable"));
        //System.out.println(list.getText());//print text
        List<WebElement> elements = list.findElements(By.xpath("//*[@id=\"srchrslt-adtable\"]/li[(contains(@class,'lazyload-item') and  not( contains(@class,'is-topad')))]"));
        for (WebElement item: elements
        ) {
            System.out.println(item.getText());
            System.out.println("==========================================================");

        }


    }

    public WGResult executeResearchNRandextract () throws IOException {

        WGResult r = new WGResult();
        System.out.println("POINT X");
        System.out.println(conf.getSearchlink());
        System.out.println("POINT Y");
        webDriver.get(conf.getSearchlink());
        WebElement list = webDriver.findElement(By.id("srchrslt-adtable"));
        //System.out.println(list.getText());//print text
        List<WebElement> elements = list.findElements(By.xpath("//*[@id=\"srchrslt-adtable\"]/li[(contains(@class,'lazyload-item') and  not( contains(@class,'is-topad')))]"));
        for (WebElement item: elements.subList(0,3)
        ) {
            //System.out.println(item.getText());

            String link = conf.getDomain()+item.findElement(By.xpath(".//article")).getAttribute("data-href");
            String itemid = item.findElement(By.xpath("./article")).getAttribute("data-adid");
            System.out.println("Link : "+ link);

            System.out.println("Id :"+ itemid);
            String imgLink = item.findElement(By.xpath(".//div[1]/a/div")).getAttribute("data-imgsrc");


            System.out.println("Img link : "+ imgLink);


            CarData car = null;
            int cnt = 0;
            do{
                car = getCarDataElement(itemid,link,imgLink);
                cnt++;
                System.out.println("Tries : "+cnt);
            }while (car == null && cnt < 5);
            if (car != null)
            {
                System.out.println(car);
                r.addCar(car);

                System.out.println("==========================================================");
            }


        }


        return r;
    }



    public CarData getCarDataElement(String uid,String urlitem, String img) throws IOException {

        tmp.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);


        tmp.get(urlitem);
        tmp.navigate().refresh();
        Hashtable<String,String> data = new Hashtable<>();

        for (String a : conf.getElementstrlst())
        {

            WebElement element = null;
            //System.out.println(conf.getElement(a).getValue());
            //System.out.println("id :"+conf.getElement(a).getByid());
            //System.out.println("xpath :"+conf.getElement(a).getByxPath());
            checkk:
            try
            {


                if(conf.getElement(a).getByid()) {
                    /*Wait<WebDriver> wait = new FluentWait<WebDriver>(webDriver)
                            .withTimeout(Duration.ofSeconds(30))
                            .pollingEvery(Duration.ofSeconds(5))
                            .ignoring(NoSuchElementException.class);

                    element = wait.until(new Function<WebDriver, WebElement>() {
                        public WebElement apply(WebDriver driver) {
                            return driver.findElement(By.id(conf.getElement(a).getValue()));
                        }
                    });*/

                    element = tmp.findElement(By.id(conf.getElement(a).getValue()));
                }
                else if (conf.getElement(a).getByxPath()){
                    element = tmp.findElement(By.xpath(conf.getElement(a).getValue()));
                }//if else for future change example : by tagname or  by name or something XD
                data.put(conf.getElement(a).getName(),element.getText());
                System.out.println("[["+conf.getElement(a).getName() +" :"+element.getText()+"]  || is required :   "+ conf.isRequired(conf.getElement(a).getName())+"]");

            }
            catch (Exception ex)
            {
                System.out.println(conf.getElement(a).getName()+"  ||  Null");
                data.put(conf.getElement(a).getName(),"null");
                if (conf.isRequired(conf.getElement(a).getName()))
                {
                    tmp.navigate().refresh();
                    return null;

                }

            }



        }
        CarDataInfo cdi = new CarDataInfo(data.get("Phone"),data.get("Contact"),urlitem);
        CarDataDescription cdd = new CarDataDescription(data.get("Title"), data.get("Brand"),data.get("Exterior color"),data.get("Millage"),data.get("Fuel Type"));


        InputStream a  = new URL(img).openStream();

        CarData cd = new CarData(uid,cdi,cdd,new ImageView(new Image(a)));

        return cd;



    }


    public void setconf(Config c) {
        this.conf = c;
    }

    public void getError() {
        System.out.println(webDriver.getCurrentUrl());
        System.out.println(tmp.getCurrentUrl());
    }
}
