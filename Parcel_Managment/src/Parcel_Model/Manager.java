package Parcel_Model;



import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.swing.*;

import Parcel_Controller.Controller;
import Parcel_View.View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Manager {
    private View view;
    
    private ParcelMap parcelMap; // ParcelMap instance
    private List<String> parcels;

    // Constructor to initialize View
    public Manager(View view) throws IOException {
        this.view = view;
        
        this.parcels = loadParcels("parcel.txt"); // Load parcels from file
    }

    // Load parcels from the parcel.txt file and return them as a List of strings
    public List<String> loadParcels(String filename) throws IOException {
        List<String> parcels = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                parcels.add(line); // Add each line as a string to the parcel list
            }
        } catch (IOException e) {
            throw new IOException("Error reading parcel file: " + e.getMessage()); // Throw exception to be handled elsewhere
        }

        return parcels;
    }
    
    public List<String> loadCustomersWithDetails(String filename) throws IOException {
        List<String> detailedCustomers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] customerData = line.split(" "); // Assuming space-separated values in customer.txt
                if (customerData.length >= 4) { // Ensure enough data exists
                    String customerId = customerData[0];
                    String firstName = customerData[1];
                    String lastName = customerData[2];
                    String parcelId = customerData[3];

                    detailedCustomers.add("Customer ID: " + customerId +
                            ", First Name: " + firstName +
                            ", Last Name: " + lastName +
                            ", Parcel ID: " + parcelId);
                }
            }
        }
        return detailedCustomers;
    }

    public List<String> loadParcelsWithDetails(String filename) throws IOException {
        List<Parcel> parcels = loadParcels1(filename); // Load parcels as objects
        List<String> detailedParcels = new ArrayList<>();
        for (Parcel parcel : parcels) {
            detailedParcels.add("Parcel ID: " + parcel.getParcelId() +
                    ", Weight: " + parcel.getWeight() +
                    ", Length: " + parcel.getLength() +
                    ", Width: " + parcel.getWidth() +
                    ", Height: " + parcel.getHeight() +
                    ", Days in Depot: " + parcel.getDaysInDepot());
        }
        return detailedParcels;
    }
    // Load customers from the customer.txt file and return them as a List of strings
    public List<String> loadCustomers(String filename) throws IOException {
        List<String> customers = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                customers.add(line); // Add each line as a string to customer
            }
        } catch (IOException e) {
            throw new IOException("Error reading customer file: " + e.getMessage()); // Throw exception to be handled elsewhere
        }

        return customers;
    }

    

    // Add customer to the system and save to both customer.txt and log.txt
    public void addCustomer(String customerId, String firstName, String lastName, String parcelId) {
        // Create a new Customer object
        Customer customer = new Customer(customerId, firstName, lastName, parcelId);

        // Save customer data to customer.txt in the format: firstName lastName customerId
        saveToFile("customer.txt", customerId + " " + firstName + " " + lastName + " " + parcelId);

        // Log customer addition to log.txt (without timestamp)
        logToFile1("log.txt", "Added customer - " + customerId + " " + firstName + " " + lastName + " " + parcelId);

        // Display message and clear input fields
        JOptionPane.showMessageDialog(view.getFrame(), "Customer Added: " + firstName + " " + lastName);
        View.clearInputFields();
    }

    // Add parcel to the system and save to file
    public void addParcel(int parcelId, double weight, double length, double width, double height, int daysInDepot) {
        Parcel parcel = new Parcel(parcelId, weight, length, width, height, daysInDepot);
        saveToFile("parcel.txt", parcelId + " " + weight + " " + length + " " + width + " " + height + " " + daysInDepot);

        // Log the event
        Log.getInstance().logToFile("log.txt", "Added parcel - ID: " + parcelId);
    }


    // Save data to a text file (e.g., customer.txt, parcel.txt) in the format specified
    private void saveToFile(String filename, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            writer.write(content);  // Write content to file
            writer.newLine();       // Add newline for each entry
        } catch (IOException e) {
            System.err.println("Error saving to file: " + e.getMessage());
        }
    }

    // Log customer and parcel addition details to the log.txt file (without timestamp)
    private void logToFile1(String filename, String logMessage) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            writer.write(logMessage);  // Write log message
            writer.newLine();  // Add newline for each log entry
        } catch (IOException e) {
            System.err.println("Error logging to file: " + e.getMessage());
        }
    }

    // Load parcels from parcel.txt file and convert each line to a Parcel object
    public List<Parcel> loadParcels1(String filename) throws IOException {
        List<Parcel> parcels = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parcelDetails = line.split(" ");
                int parcelId = Integer.parseInt(parcelDetails[0]);
                double weight = Double.parseDouble(parcelDetails[1]);
                double length = Double.parseDouble(parcelDetails[2]);
                double width = Double.parseDouble(parcelDetails[3]);
                double height = Double.parseDouble(parcelDetails[4]);
                int daysInDepot = Integer.parseInt(parcelDetails[5]);
                parcels.add(new Parcel(parcelId, weight, length, width, height, daysInDepot));
            }
        } catch (IOException e) {
            throw new IOException("Error reading parcel file: " + e.getMessage());
        }
        return parcels;
    }

    
    public Parcel processParcel(int parcelId) throws IOException {
        List<Parcel> parcels = loadParcels1("parcel.txt");
        Parcel processedParcel = null;

        for (int i = 0; i < parcels.size(); i++) {
            if (parcels.get(i).getParcelId() == parcelId) {
                processedParcel = parcels.remove(i);
                break;
            }
        }

        if (processedParcel != null) {
            // Save remaining parcels back to parcel.txt
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("parcel.txt"))) {
                for (Parcel parcel : parcels) {
                    writer.write(parcel.getParcelId() + " " + parcel.getWeight() + " " + parcel.getLength() + " "
                            + parcel.getWidth() + " " + parcel.getHeight() + " " + parcel.getDaysInDepot());
                    writer.newLine();
                }
            }

            // Log processed parcel with identifier
            Log.getInstance().logToFile("log.txt", processedParcel.toString());
        }

        return processedParcel;
    }
    
    
    

    public static void main(String[] args) {
        // Initialize the view (GUI)
        View view = new View();
        

        // Use a try-catch block to handle the IOException thrown by the Manager constructor
        try {
            // Create the Manager object to handle the logic
            Manager manager = new Manager(view);

            // Set up the Controller to handle interactions between View and Manager
            Controller controller = new Controller(view, manager);
        } catch (IOException e) {
            // Handle the error if loading parcels fails
            System.err.println("Error initializing the application: " + e.getMessage());
            // Optionally, you can display an error dialog
            JOptionPane.showMessageDialog(null, "Error initializing the application: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
