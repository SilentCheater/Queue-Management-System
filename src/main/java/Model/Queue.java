package Model;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Queue implements Runnable {
    private BlockingQueue<Client> clients;
    private AtomicInteger waitingTime;
    private AtomicInteger nrClients;
    private AtomicInteger waitingTimeSum;


    public Queue() {
        this.waitingTime = new AtomicInteger();
        clients = new ArrayBlockingQueue<Client>(1000);
        nrClients = new AtomicInteger();
        waitingTimeSum = new AtomicInteger();
    }

    public AtomicInteger getWaitingTime() {
        return waitingTime;
    }

    public void addClient(Client client){
        clients.add(client);
        waitingTime.addAndGet(client.getServiceTime());
        nrClients.addAndGet(1);
    }

    @Override
    public synchronized void run() {
       // while(!die){
        if(clients.size()!=0) {
            if (clients.peek().getServiceTime() == 0) {
                try {
                    Globals.file.write("Client #" + clients.peek().getID() + " left\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    clients.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (clients.size()!=0 &&clients.peek().getServiceTime() > 0) {
                try {
                    Globals.file.write("Client #" + clients.peek().getID() + " remaining time: " + clients.peek().getServiceTime()+"\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                waitingTime.decrementAndGet();
                clients.peek().decrementServiceTime();
            }
        }
    }

    public AtomicInteger getNrClients() {
        return nrClients;
    }

    public AtomicInteger getWaitingTimeSum() {
        return waitingTimeSum;
    }

}
