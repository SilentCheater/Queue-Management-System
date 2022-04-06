package Model;

public class PeakHour {
    private int time;
    private int waitingTime;

    public PeakHour(){
        time=0;
        waitingTime=0;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(int waitingTime) {
        this.waitingTime = waitingTime;
    }
}
