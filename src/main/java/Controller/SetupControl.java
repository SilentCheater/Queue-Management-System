package Controller;

import View.SetupView;
import View.SimulationView;
import org.apache.commons.lang3.math.NumberUtils;

import javax.swing.*;

import static Model.Globals.*;

public class SetupControl {
    SetupView setupView = new SetupView();
    public SetupControl(){
        setupView.getSubmit().addActionListener(
                e -> {
                        if(allNumbers() && logic()) {
                            // start the simulation thread if all conditions are met
                            Thread simulationControl = new Thread(new SimulationControl(new SimulationView()));
                            // close the previous window
                            setupView.dispose();
                            simulationControl.start();
                        }
                }
        );
    }

    private boolean logic() {
        // checks for data validity and returns an error message
        if(minService.get()>maxService.get()){
            JOptionPane.showMessageDialog(null, "min service can't be bigger than max service", "min>max", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(minArrival.get()>maxArrival.get()){
            JOptionPane.showMessageDialog(null, "min arrival can't be bigger than max arrival", "min>max", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(clientsNr.get()<0 || tMax.get()<0 || maxArrival.get()<0 || minArrival.get() <0 || queuesNr.get()<0 || maxService.get()<0 || minService.get()<0)
        {
            JOptionPane.showMessageDialog(null, "values should be positive", "negative nr", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(maxArrival.get()>tMax.get()){
            JOptionPane.showMessageDialog(null, "max arrival time can't be greater than max simulation time", "max arrival > max simulation", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private boolean allNumbers() {
        // validates all the inputs to be numbers

        if(NumberUtils.isNumber(setupView.getClients().getText()))
            clientsNr.set(Integer.parseInt(setupView.getClients().getText()));
        else {
            JOptionPane.showMessageDialog(null, "Nr of clients is not a number", "NaN", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(NumberUtils.isNumber(setupView.gettMax().getText()))
            tMax.set(Integer.parseInt(setupView.gettMax().getText()));
        else {
            JOptionPane.showMessageDialog(null, "Max simulation time is not a number", "NaN", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(NumberUtils.isNumber(setupView.gettMaxArrival().getText()))
            maxArrival.set(Integer.parseInt(setupView.gettMaxArrival().getText()));
        else {
            JOptionPane.showMessageDialog(null, "maxArrival is not a number", "NaN", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(NumberUtils.isNumber(setupView.gettMaxService().getText()))
            maxService.set(Integer.parseInt(setupView.gettMaxService().getText()));
        else {
            JOptionPane.showMessageDialog(null, "maxService is not a number", "NaN", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(NumberUtils.isNumber(setupView.getQueues().getText()))
            queuesNr.set(Integer.parseInt(setupView.getQueues().getText()));
        else {
            JOptionPane.showMessageDialog(null, "Nr of queues is not a number", "NaN", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(NumberUtils.isNumber(setupView.gettMinArrival().getText()))
            minArrival.set(Integer.parseInt(setupView.gettMinArrival().getText()));
        else {
            JOptionPane.showMessageDialog(null, "minArrival is not a number", "NaN", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(NumberUtils.isNumber(setupView.gettMinService().getText()))
            minService.set(Integer.parseInt(setupView.gettMinService().getText()));
        else {
            JOptionPane.showMessageDialog(null, "minService is not a number", "NaN", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

}
