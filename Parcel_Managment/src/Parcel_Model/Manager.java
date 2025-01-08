package Parcel_Model;



import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.swing.*;
import javax.swing.text.View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.ModuleLayer.Controller;

public class Manager {
    private View view;
    
    private ParcelMap parcelMap; // ParcelMap instance
    private List<String> parcels;

    // Constructor to initialize View
    public Manager(View view) throws IOException {
        this.view = view;
        
        
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

    
    // Save data to a text file 
    private void saveToFile(String filename, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            writer.write(content);  // Write content to file
            writer.newLine();       // Add newline for each entry
        } catch (IOException e) {
            System.err.println("Error saving to file: " + e.getMessage());
        }
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
