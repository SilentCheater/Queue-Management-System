package Model;

import java.util.concurrent.atomic.AtomicInteger;

public class Client {
    private final int ID;
    private final int arrivalTime;
    private int serviceTime;
    private int remainingTime;

    public Client(int id, int arrivalTime, int serviceTime) {
        ID = id;
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
    }

    public int getID() {
        return ID;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getServiceTime() {
        return serviceTime;
    }
    public void decrementServiceTime() {
        this.serviceTime--;
    }

    public int getRemainingTime() {
        return remainingTime;
    }

    public void setRemainingTime(int remainingTime) {
        this.remainingTime = remainingTime;
    }
}
