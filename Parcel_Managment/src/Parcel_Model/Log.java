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
 // Remove this method if not needed
    public void logToFile(String filename, String logMessage) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            writer.write("ProcessedParcel: " + logMessage); // Add identifier
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error logging to file: " + e.getMessage());
        }
    }



}