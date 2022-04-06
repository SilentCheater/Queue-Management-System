package Controller;

import Model.Client;
import Model.Globals;
import Model.PeakHour;
import Model.Queue;
import View.ClientBox;
import View.SimulationView;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import static Model.Globals.*;
import static java.lang.Thread.sleep;

public class SimulationControl implements Runnable {
    SimulationView simulationView;
    private ArrayList<Queue> threads;
    private ArrayList<Client> clients;
    private int currentTime = 0;
    private double averageServiceTime=0;
    PeakHour peakHour = new PeakHour();


    public SimulationControl(SimulationView simulationView){

        this.simulationView=simulationView;

        // initialise as many queues (threads) as requested
        threads = new ArrayList<>();
        for(int i = 0; i< queuesNr.get(); i++){
            threads.add(new Queue());
            Thread t = new Thread(threads.get(i));
            t.start();
        }

        // generate nrClients random clients
        Random rand = new Random();
        clients = new ArrayList<>();
        for(int i = 0; i< clientsNr.get(); i++) {
            clients.add(new Client(i, rand.nextInt(minArrival.get(), maxArrival.get() + 1), rand.nextInt(minService.get(), maxService.get() + 1)));
            try {
                Globals.file.write("Client #"+clients.get(i).getID()+ " arrives at: "+clients.get(i).getArrivalTime()+" with service: "+clients.get(i).getServiceTime()+"\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
            averageServiceTime += clients.get(i).getServiceTime();
        }
        averageServiceTime = (double) averageServiceTime / clientsNr.get();
    }

    private Queue bestQueue(){
        // find the best queue based on the lowest waiting time
        int min = Integer.MAX_VALUE;
        Queue queue = new Queue();
        // go through all threads to find the best one
        for(Queue thread : threads){
            if(thread.getWaitingTime().get()< min){
                min = thread.getWaitingTime().get();
                queue = thread;
            }
        }
        return queue;
    }

    private void addClient(Client client, Queue queue){
        // check if there is enough time to serve the client in any of the queues
        if((client.getServiceTime()+ queue.getWaitingTime().get()+ currentTime) > tMax.get()) {
            try {
                Globals.file.write("Client #" + client.getID() + " can't be added because he will not be able to finish his task in time.\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            // add the client to the selected queue (thread backend)
            queue.addClient(client);
            // add it to the frontend also
            simulationView.getQueues().get(threads.indexOf(queue)).add(new ClientBox(client));
            // additional variable to store the total waiting times to compute the average waiting time
            queue.getWaitingTimeSum().addAndGet(queue.getWaitingTime().get());
            // increment the total number of clients that entered the queue
            queue.getNrClients().addAndGet(1);
            // set the individual waiting time
            client.setRemainingTime(queue.getWaitingTime().get());
            try {
                Globals.file.write("Client #" + client.getID() + " added to thread #" + threads.indexOf(queue)+"\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public synchronized void run() {
        while (currentTime <= tMax.get()) {
            try {
                // simulates the real time
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                Globals.file.write(currentTime + ":\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
            for (Client client : clients) {
                // go through clients and if the arrival time is the same as the current time
                if (client.getArrivalTime() == currentTime) {
                    // find the best queue in terms of waiting time
                    Queue best = bestQueue();
                    // and add the client to that queue (using the special method implemented in this class
                    addClient(client, best);
                }
                // decrease the waiting time because a moment passed
                client.setRemainingTime(client.getRemainingTime()-1);
            }
            // start the execution of each thread for 1 period of time
            int peakHourSum = 0;
            for (Queue queue : threads) {
                queue.run();
                peakHourSum += queue.getWaitingTime().get();
            }

            if(peakHourSum>peakHour.getWaitingTime()){
                peakHour.setTime(currentTime);
                peakHour.setWaitingTime(peakHourSum);
            }
            // update the gui
            simulationView.update();
            simulationView.getProgressBar().setValue(currentTime);
            simulationView.getCurrentTimeLabel().setText("Current time: " + currentTime + "/" + Globals.tMax.get());

            currentTime++;
        }
        for(Queue queue : threads){
            try {
                Globals.file.write("Average waiting time for thread #" + threads.indexOf(queue) + ": "+ Math.floor((double) queue.getWaitingTimeSum().get()/queue.getNrClients().get() *100)/100 +"\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            Globals.file.write("Average service time: "+ Math.floor(averageServiceTime *100)/100+"\n" );
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Globals.file.write("Peak hour: " + peakHour.getTime() + " with average waiting time: " + Math.floor ((double) peakHour.getWaitingTime() / queuesNr.get()*100)/100+"\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        JOptionPane.showMessageDialog(null, "simulation finished", "end sim", JOptionPane.INFORMATION_MESSAGE);
        try {
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
