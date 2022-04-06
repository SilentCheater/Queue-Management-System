package Model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

public class Globals {
    public static AtomicInteger tMax = new AtomicInteger(30);
    public static AtomicInteger clientsNr = new AtomicInteger(17);
    public static AtomicInteger queuesNr = new AtomicInteger(2);
    public static AtomicInteger minArrival = new AtomicInteger(1);
    public static AtomicInteger maxArrival = new AtomicInteger(10);
    public static AtomicInteger minService = new AtomicInteger(2);
    public static AtomicInteger maxService = new AtomicInteger(5);
    public static File fileInit = new File("log.txt");
    public static FileWriter file;

    static {
        try {
            file = new FileWriter("log.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
