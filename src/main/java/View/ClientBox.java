package View;

import Model.Client;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ClientBox extends JTextArea {
    AtomicInteger initialServiceTime = new AtomicInteger();
    Client client;

    public ClientBox(Client client){
        this.setFont(new Font("Consolas", Font.PLAIN, 11));
        this.setForeground(new Color(0xF4DECB));
        this.setBackground(new Color(0x262228));
        this.client = client;
        initialServiceTime.set(client.getServiceTime());
        this.setText("ID: "+client.getID()+ "\nArrival time: "+ client.getArrivalTime() + "\nService time: "+initialServiceTime
                    +"\n Remaining time: "+client.getRemainingTime());
        this.setEditable(false);
    }
    public void setTop(){
        this.setBackground(new Color(0x007849));
    }

    public void updateClientBox(){
        this.setText("ID: "+this.client.getID()+ "\nArrival time: "+ this.client.getArrivalTime() + "\nService time: "+initialServiceTime
                +"\nRemaining time: "+this.client.getRemainingTime());
    }
}
