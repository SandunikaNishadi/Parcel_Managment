package Parcel_Controller;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;


import Parcel_Model.Manager;
import Parcel_View.View;



public class Controller {
    private View view;
    private Manager manager;

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
        
        this.view.getProcessButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processParcel();
            }
        });
        this.view.getProcessButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processParcel();
            }
        });

        // Add action listener for "Processed Parcels" button
        this.view.getProcessedParcelsButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showProcessedParcels();
            }
        });

        // Add action listener to the Add Parcel button
        this.view.getAddParcelButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addParcel();
            }
        });
        
        // Add action listener to the load Parcels button
        this.view.getLoadParcelsButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadParcels();
            }
        });

        // Add action listener to the load Customers button
        this.view.getLoadCustomersButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadCustomers();
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

   
    
    // Method to handle the loadCustomers button action
    private void loadCustomers() {
        try {
            List<String> detailedCustomers = manager.loadCustomersWithDetails("customer.txt"); // Get detailed customer descriptions
            if (detailedCustomers.isEmpty()) {
                JOptionPane.showMessageDialog(view.getFrame(), "No customers to display.", "Info", JOptionPane.INFORMATION_MESSAGE);
            } else {
                view.setParcelListData(detailedCustomers); // Update the list in the GUI with detailed customers
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(view.getFrame(), "Error loading customers: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
    private void processParcel() {
        try {
            String parcelIdStr = view.getParcelIdField().getText().trim();
            if (parcelIdStr.isEmpty()) {
                JOptionPane.showMessageDialog(view.getFrame(), "Please enter a Parcel ID.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int parcelId = Integer.parseInt(parcelIdStr);
            Parcel processedParcel = manager.processParcel(parcelId);

            if (processedParcel == null) {
                JOptionPane.showMessageDialog(view.getFrame(), "Parcel ID not found.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                double fee = processedParcel.getWeight() * 1;
                JOptionPane.showMessageDialog(view.getFrame(),
                        "Processed Parcel:\n" + processedParcel.toString() + "\nFee: $" + fee);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view.getFrame(), "Invalid Parcel ID. Please enter a numeric value.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(view.getFrame(), "Error processing parcel: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Method to display all processed parcels
    private void showProcessedParcels() {
        try {
            List<String> processedParcels = manager.loadProcessedParcels("log.txt");
            if (processedParcels.isEmpty()) {
                JOptionPane.showMessageDialog(view.getFrame(), "No processed parcels to display.", "Info", JOptionPane.INFORMATION_MESSAGE);
            } else {
                view.setParcelListData(processedParcels); // Display filtered processed parcels
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(view.getFrame(), "Error loading processed parcels: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    // Method to display all processed parcels



    // Handle the action for adding a parcel
    private void addParcel() {
        try {
            int parcelId = Integer.parseInt(view.getParcelIdParcelField().getText().trim());
            double weight = Double.parseDouble(view.getWeightField().getText().trim());
            double length = Double.parseDouble(view.getLengthField().getText().trim());
            double width = Double.parseDouble(view.getWidthField().getText().trim());
            double height = Double.parseDouble(view.getHeightField().getText().trim());
            int daysInDepot = Integer.parseInt(view.getDaysInDepotField().getText().trim());

            // Validate inputs
            if (weight <= 0 || length <= 0 || width <= 0 || height <= 0 || daysInDepot < 0) {
                JOptionPane.showMessageDialog(view.getFrame(), "Dimensions, weight, and days in depot must be positive values.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Pass data to the Manager to handle business logic
            manager.addParcel(parcelId, weight, length, width, height, daysInDepot);
            JOptionPane.showMessageDialog(view.getFrame(), "Parcel added successfully!");
            view.clearInputFields();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view.getFrame(), "Invalid input for Parcel data.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    
}
