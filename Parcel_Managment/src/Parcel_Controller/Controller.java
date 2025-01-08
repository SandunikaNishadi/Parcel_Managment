package Parcel_Controller;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class Controller {
    

    public Controller(View view, Manager manager) {
        this.view = view;
        this.manager = manager;

        // Add action listener to the Add Customer button
        this.view.getAddCustomerButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addCustomer();
            }
        });
        
    }

    // Method to handle the loadParcels button action
    private void loadParcels() {
        try {
            List<String> detailedParcels = manager.loadParcelsWithDetails("parcel.txt"); // Get detailed parcel descriptions
            if (detailedParcels.isEmpty()) {
                JOptionPane.showMessageDialog(view.getFrame(), "No parcels to display.", "Info", JOptionPane.INFORMATION_MESSAGE);
            } else {
                view.setParcelListData(detailedParcels); // Update the list in the GUI with detailed parcels
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(view.getFrame(), "Error loading parcels: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    
    

    // Handle the action for adding a customer
    private void addCustomer() {
        try {
            String firstName = view.getFirstNameField().getText().trim();
            String lastName = view.getLastNameField().getText().trim();
            String customerId = view.getCustomerIdField().getText().trim();
            String parcelId = view.getParcelIdCustomerField().getText().trim();

            if (firstName.isEmpty() || lastName.isEmpty()) {
                JOptionPane.showMessageDialog(view.getFrame(), "First name and last name cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Pass data to the Manager to handle business logic
            manager.addCustomer(customerId, firstName, lastName, parcelId);
            JOptionPane.showMessageDialog(view.getFrame(), "Customer added successfully!");
            view.clearInputFields();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view.getFrame(), "Invalid input for Customer ID or Parcel ID.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
      



    
}
