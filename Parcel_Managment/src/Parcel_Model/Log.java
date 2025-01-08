package Parcel_Model;



import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Log {
    private static Log instance;
    private StringBuilder log;

    private Log() {
        log = new StringBuilder();
    }

    public static Log getInstance() {
        if (instance == null) {
            instance = new Log();
        }
        return instance;
    }

    public void addLog(String event) {
        log.append(event).append("\n");
    }

    public String getLog() {
        return log.toString();
    }
 



}