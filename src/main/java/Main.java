import Controller.SetupControl;
import Model.Globals;

import java.io.IOException;

public class Main {
    public static void main(String[] args){
        try {
            Globals.fileInit.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        new SetupControl();
    }
}
