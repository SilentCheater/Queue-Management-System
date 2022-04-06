package View;


import Model.Client;
import Model.Globals;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SimulationView extends JFrame {
    private ArrayList<JPanel> queues;
    JProgressBar progressBar;
    JPanel queuesPanel;
    JPanel singleQueue;
    JPanel progress;
    JLabel currentTimeLabel = new JLabel();

    public SimulationView(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        queues = new ArrayList<>(Globals.queuesNr.get());

        //testing

        //progress - top
        progress = new JPanel();
        progress.setBackground(new Color(0xEB6E80));


        currentTimeLabel.setText("Current time: " + 0 + "/" + Globals.tMax.get());
        progress.add(currentTimeLabel);

        progressBar = new JProgressBar();
        progressBar.setMaximum(Globals.tMax.get());
        progressBar.setBorder(null);
        progress.add(progressBar);

        this.add(progress, BorderLayout.NORTH);


        //queues

        queuesPanel = new JPanel();
        queuesPanel.setBackground(new Color(0xFFCE00));
        queuesPanel.setLayout(new GridLayout(Globals.queuesNr.get(), 1, 20, 5));

        //adding all queues into a main panel and also store them in arraylist
        for(int i = 0;i<Globals.queuesNr.get();i++){
            singleQueue = new JPanel();
            singleQueue.setBackground(new Color(0x0375B4));
            singleQueue.setLayout(new GridLayout(1, Globals.clientsNr.get(), 20, 50));
            singleQueue.setPreferredSize(new Dimension(1500, 100));
            singleQueue.add(new JLabel("Queue #"+i));
            queues.add(singleQueue);
            queuesPanel.add(singleQueue);
        }


        this.add(queuesPanel, BorderLayout.CENTER);


        this.setVisible(true);
        this.pack();
    }

    public ArrayList<JPanel> getQueues() {
        return queues;
    }
    public JProgressBar getProgressBar(){
        return progressBar;
    }
    public JLabel getCurrentTimeLabel() {
        return currentTimeLabel;
    }

    public synchronized void update(){
        // go through the panels
        for(JPanel panel : queues){
            // check for the panel to have at least 2 components (the label and at least one client)
            if(panel.getComponents().length>1) {
                // update the first client representation - makes it green
                ClientBox clientBox1 = (ClientBox) panel.getComponent(1);
                clientBox1.setTop();
                // make a list out of all the components of the panel
                List<Component> boxes = Arrays.stream(panel.getComponents()).toList();
                boolean remove = false;
                // go through all the ClientBoxes
                for (Component box : boxes) {
                    // ignore the first component as it is a label and it doesn't need updates
                    if (box != boxes.get(0)) {
                        ClientBox box1 = (ClientBox) box;
                        // update the "box" after casting from Component to ClientBox
                        box1.updateClientBox();
                        // check for removing
                        if (box1.client.getServiceTime() == 0)
                            remove = true;
                    }
                }
                // if the first client finished his task, it will be removed
                if (remove == true)
                    panel.remove(1);
                // "repaint" the new first element in the queue
                if(panel.getComponents().length>1) {
                    ClientBox clientBox2 = (ClientBox) panel.getComponent(1);
                    clientBox2.setTop();
                }
            }
        }
    }
}
