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

    // Method to remove customer by customerId
    public boolean removeCustomer(String customerId) throws IOException {
        // Remove the customer from the list of customers
        List<String> customers = loadCustomers("customer.txt");
        boolean removed = false;

        for (int i = 0; i < customers.size(); i++) {
            String customer = customers.get(i);
            if (customer.startsWith(customerId)) { // Assuming the customerId is the first part of the line
                customers.remove(i); // Remove customer from the list
                removed = true;
                break;
            }
        }

        if (removed) {
            // Rewrite the customer.txt file without the removed customer
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("customer.txt"))) {
                for (String customer : customers) {
                    writer.write(customer);  // Write remaining customers to file
                    writer.newLine();
                }
                // Log the removal action
                logAction("log.txt", customerId);
                JOptionPane.showMessageDialog(view.getFrame(), "Customer with ID " + customerId + " removed successfully");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(view.getFrame(), "Error saving changes to customer file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            // If the customer was not found
            JOptionPane.showMessageDialog(view.getFrame(), "Customer ID not found", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return removed;
    }

    // Method to log the customer removal action
    public static void logAction(String logFile, String customerId) {
        try (BufferedWriter logWriter = new BufferedWriter(new FileWriter(logFile, true))) {
            // Log the removal action with the customer ID and timestamp
            logWriter.write("Customer ID " + customerId + " removed at " + System.currentTimeMillis());
            logWriter.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        view.clearInputFields();
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
    public List<String> loadProcessedParcels(String logFile) throws IOException {
        List<String> processedParcels = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(logFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("ProcessedParcel:")) { // Only include relevant entries
                    processedParcels.add(line.replace("ProcessedParcel: ", "")); // Remove the identifier
                }
            }
        }
        return processedParcels;
    }
    public List<Parcel> getParcelsSortedByDaysInDepot(String filename) throws IOException {
        List<Parcel> parcels = loadParcels1(filename); // Load parcels from parcel.txt
        parcels.sort(Comparator.comparingInt(Parcel::getDaysInDepot).reversed()); // Sort by days in depot, descending
        return parcels;
    }
    public double calculateFee(int parcelId) {
        try {
            List<Parcel> parcels = loadParcels1("parcel.txt"); // Load parcels from file
            for (Parcel parcel : parcels) {
                if (parcel.getParcelId() == parcelId) { // Match the parcel ID
                    return parcel.getWeight() * 1; // Fee = weight * 1
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading parcels: " + e.getMessage());
        }
        return -1; // Return -1 if the parcel is not found
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