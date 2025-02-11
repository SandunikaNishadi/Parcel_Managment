package Parcel_Controller;
import Parcel_Model.Manager;
import Parcel_Model.Parcel;
import Parcel_View.View;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

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
        this.view.getSortByDaysInDepotButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sortParcelsByDaysInDepot();
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

        // Add action listener to the Remove Customer button
        this.view.getRemoveCustomerButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeCustomer();
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

    private void sortParcelsByDaysInDepot() {
        try {
            List<Parcel> sortedParcels = manager.getParcelsSortedByDaysInDepot("parcel.txt"); // Get sorted parcels
            List<String> parcelDetails = new ArrayList<>();
            for (Parcel parcel : sortedParcels) {
                parcelDetails.add(parcel.toString()); // Convert parcels to string representation
            }
            view.setParcelListData(parcelDetails); // Update the left-side list in the GUI
        } catch (IOException e) {
            JOptionPane.showMessageDialog(view.getFrame(), "Error loading or sorting parcels: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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

            // Process the parcel and log it
            Parcel processedParcel = manager.processParcel(parcelId);
            if (processedParcel == null) {
                JOptionPane.showMessageDialog(view.getFrame(), "Parcel ID not found in parcel.txt.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                double fee = processedParcel.getWeight() * 1;
                JOptionPane.showMessageDialog(view.getFrame(),
                        "Processed Parcel:\n" + processedParcel.toString() + "\nFee: $" + fee);

                // Fetch processed parcel details from log.txt
                String processedDetails = manager.getProcessedParcelDetails(parcelId, "log.txt");
                if (processedDetails != null) {
                    view.setParcelListData(List.of(processedDetails)); // Display only the relevant details
                } else {
                    JOptionPane.showMessageDialog(view.getFrame(), "Error retrieving processed parcel details.", "Error", JOptionPane.ERROR_MESSAGE);
                }
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
            List<String> processedParcels = manager.loadProcessedParcels("log.txt"); // Fetch only processed parcels
            if (processedParcels.isEmpty()) {
                JOptionPane.showMessageDialog(view.getFrame(), "No processed parcels to display.", "Info", JOptionPane.INFORMATION_MESSAGE);
            } else {
                view.setParcelListData(processedParcels); // Display the filtered list
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

    // Handle the action for removing a customer
    private void removeCustomer() {
        try {
            String customerId = view.getRemovecustomer().getText().trim();

            if (customerId.isEmpty()) {
                JOptionPane.showMessageDialog(view.getFrame(), "Customer ID cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            boolean isRemoved = manager.removeCustomer(customerId); // Call Manager's removeCustomer method

            if (isRemoved) {
                JOptionPane.showMessageDialog(view.getFrame(), "Customer removed successfully!");
            } else {
                JOptionPane.showMessageDialog(view.getFrame(), "Customer ID not found.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view.getFrame(), "An error occurred while removing the customer.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
