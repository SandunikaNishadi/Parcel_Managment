package Parcel_Model;



public class Log {
    private static Log instance;
    private StringBuilder log;

    private Log() {
        log = new StringBuilder();
    }
}