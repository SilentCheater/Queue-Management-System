package View;

import javax.swing.*;
import java.awt.*;

public class SetupView extends JFrame {
        JLabel n = new JLabel("Insert the number of clients: ");
        JTextField clients = new JTextField();
        JLabel q = new JLabel("Insert the number of queues: ");
        JTextField queues = new JTextField();
        JLabel sim = new JLabel("Insert the maximum time simulation: ");
        JTextField tMax = new JTextField();
        JLabel minArrival = new JLabel("Insert the minimum arrival time: ");
        JTextField tMinArrival = new JTextField();
        JLabel maxArrival = new JLabel("Insert the maximum arrival time: ");
        JTextField tMaxArrival = new JTextField();
        JLabel minService = new JLabel("Insert the minimum service time: ");
        JTextField tMinService = new JTextField();
        JLabel maxService = new JLabel("Insert the maximum service time: ");
        JTextField tMaxService = new JTextField();
        JButton submit = new JButton("SUBMIT");

        public SetupView(){
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(15, 1, 50, 5));

            panel.setBackground(new Color(0xFFCE00));
            n.setForeground(new Color(0x007849));
            clients.setBackground(new Color(0x262228));
            clients.setForeground(new Color(0x0375B4));
            clients.setBorder(null);
            clients.setCaretColor(new Color(0x0375B4));
            panel.add(n);
            panel.add(clients);
            panel.add(q);
            q.setForeground(new Color(0x007849));
            panel.add(queues);
            queues.setBackground(new Color(0x262228));
            queues.setForeground(new Color(0x0375B4));
            queues.setBorder(null);
            queues.setCaretColor(new Color(0x0375B4));
            panel.add(sim);
            sim.setForeground(new Color(0x007849));
            panel.add(tMax);
            tMax.setBackground(new Color(0x262228));
            tMax.setForeground(new Color(0x0375B4));
            tMax.setBorder(null);
            tMax.setCaretColor(new Color(0x0375B4));
            panel.add(minArrival);
            minArrival.setForeground(new Color(0x007849));
            panel.add(tMinArrival);
            tMinArrival.setBackground(new Color(0x262228));
            tMinArrival.setForeground(new Color(0x0375B4));
            tMinArrival.setBorder(null);
            tMinArrival.setCaretColor(new Color(0x0375B4));
            panel.add(maxArrival);
            maxArrival.setForeground(new Color(0x007849));
            panel.add(tMaxArrival);
            tMaxArrival.setBackground(new Color(0x262228));
            tMaxArrival.setForeground(new Color(0x0375B4));
            tMaxArrival.setBorder(null);
            tMaxArrival.setCaretColor(new Color(0x0375B4));
            panel.add(minService);
            minService.setForeground(new Color(0x007849));
            panel.add(tMinService);
            tMinService.setBackground(new Color(0x262228));
            tMinService.setForeground(new Color(0x0375B4));
            tMinService.setBorder(null);
            tMinService.setCaretColor(new Color(0x0375B4));
            panel.add(maxService);
            maxService.setForeground(new Color(0x007849));
            panel.add(tMaxService);
            tMaxService.setBackground(new Color(0x262228));
            tMaxService.setForeground(new Color(0x0375B4));
            tMaxService.setBorder(null);
            tMaxService.setCaretColor(new Color(0x0375B4));
            panel.add(submit);
            this.add(panel);
            this.setVisible(true);
            this.pack();
        }

    public JButton getSubmit() {
        return submit;
    }

    public JTextField getClients() {
        return clients;
    }

    public JTextField getQueues() {
        return queues;
    }

    public JTextField gettMax() {
        return tMax;
    }

    public JTextField gettMaxArrival() {
        return tMaxArrival;
    }

    public JTextField gettMaxService() {
        return tMaxService;
    }

    public JTextField gettMinArrival() {
        return tMinArrival;
    }

    public JTextField gettMinService() {
        return tMinService;
    }
}
