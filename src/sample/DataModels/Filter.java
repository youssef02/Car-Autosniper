package sample.DataModels;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;
import sample.Webgator.WGResult;
import sample.Webgator.WGservice;

import java.util.ArrayList;

public class Filter {
    //todo implement runnable (separate from UI thread)


    //App has a list of filters that control and get data
    private String Name;
    private String Description;
    private Status status;
    private  String stat;
    private Boolean S_start = false;
    private FilterOption fo;

    public ObservableList<CarData> getData() {
        return data;
    }

    private ObservableList<CarData> data;
    private WGservice WGS;
    public Filter() {
        data = FXCollections.observableList(new ArrayList<CarData>());
        WGS = new WGservice();
        WGS.setOnSucceeded(new EventHandler<WorkerStateEvent>() {

            @Override
            public void handle(WorkerStateEvent t) {
                System.out.println("Job Done");

                WGResult r = (WGResult) t.getSource().getValue();
                if (r == null)
                {
                    System.out.println("No data");
                    return;
                }
                for (CarData car : r.getCarslist())
                {
                    if (!carfound(car))
                    {

                        data.add(car);
                        System.out.println(car.getUID()+ " Added");
                    }
                }


            }
        });
        WGS.setOnRunning(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent workerStateEvent) {
                System.out.println("Job running");
                stat="running";
            }
        });
        WGS.setOnFailed(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent workerStateEvent) {
                WGS.getException().printStackTrace(System.err);
                status = Status.FAILED;
                stat="failed";


            }
        });
    }

    public Filter(FilterOption fo) {
        data = FXCollections.observableList(new ArrayList<CarData>());
        this.fo = fo;
        setStatus(Status.IDLE);
        stat = "Idle";
        WGS = new WGservice(this.fo);
        WGS.setOnSucceeded(new EventHandler<WorkerStateEvent>() {

            @Override
            public void handle(WorkerStateEvent t) {
                System.out.println("Job Done");
                System.out.println(t.getSource().getValue());

            }
        });
        WGS.setOnRunning(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent workerStateEvent) {
                System.out.println("Job running");
                stat="running";
            }
        });
        WGS.setOnFailed(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent workerStateEvent) {
                WGS.getException().printStackTrace(System.err);
                status = Status.FAILED;
                stat="failed";


            }
        });

    }

    public int getcount()
    {
        return data.size();
    }

    private boolean carfound(CarData car) {
        for (CarData tmp : data)
        {
            if(tmp.getUID().equals(car.getUID()))
            {
                return true;
            }
        }
        return false;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public Boolean getS_start() {
        return S_start;
    }

    public void setS_start(Boolean s_start) {
        S_start = s_start;
    }

    public FilterOption getFo() {
        return fo;
    }

    public void setFo(FilterOption fo) {

        this.fo = fo;
        System.out.println("Filter option : "+fo);
        System.out.println("POINT1");
        this.WGS.SetFilterOpt(this.fo);
    }

    public Status getStatus() {
        return status;
    }

    private void setStatus(Status status) {
        this.status = status;
    }

    public void Start() {
        setStatus(Status.RUNNING);
        stat = "running";
        if (!WGS.isRunning())
        {
            WGS.setRestartOnFailure(false);
            WGS.setDelay(Duration.seconds(2));
            WGS.reset();
            System.out.println("Setup Config X");

            WGS.StartTheService();
        }

    }

    public void Stop() {
        setStatus(Status.IDLE);
        stat = "Idle";
        WGS.cancel();
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    private enum Status {
        IDLE,
        RUNNING,
        FAILED


    }
}
