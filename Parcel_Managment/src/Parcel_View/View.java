package Parcel_View;




import javax.swing.*;
import javax.swing.border.EmptyBorder;
import Parcel_Model.Customer;

import java.awt.*;
import java.util.List;

public class View {

    private JFrame frame;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField customerIdField;
    private JTextField parcelIdCustomerField;
    private JTextField parcelIdParcelField;
    private JTextField weightField;
    private JTextField lengthField;
    private JTextField widthField;
    private JTextField heightField;
    private JTextField daysInDepotField;
    private JList<String> customerList; // JList for customers
    private JList<String> parcelList;  // JList to display parcel data
    private JButton addCustomerButton;
    private JButton addParcelButton;
    private JButton loadParcelsButton; // Declare the button
    private JButton ProcessButton;
    private JTextField ParcelIdfield;
    private JTextField removecustomer;
    private JButton removecustomerButton;
    private JButton loadCustomersButton;
    private JButton processedParcelsButton;
    private JButton sortByDaysInDepotButton;

    public View() {
        // Initialize JFrame
        frame = new JFrame("Parcel Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(Color.decode("#DEAA79"));

        // Panel for customer form
        JPanel customerPanel = new JPanel(new GridLayout(6, 2, 5, 5)); // (horizontal, vertical spacing)
        customerPanel.setBorder(new EmptyBorder(10, 10, 10, 10));  // Adds padding around the panel
        customerPanel.add(new JLabel("First Name:"));
        firstNameField = new JTextField();
        firstNameField.setBorder(new EmptyBorder(5, 5, 5, 5));
        customerPanel.add(firstNameField);

        customerPanel.add(new JLabel("Last Name:"));
        lastNameField = new JTextField();
        lastNameField.setBorder(new EmptyBorder(5, 5, 5, 5));
        customerPanel.add(lastNameField);

        customerPanel.add(new JLabel("Customer ID:"));
        customerIdField = new JTextField();
        customerIdField.setBorder(new EmptyBorder(5, 5, 5, 5));
        customerPanel.add(customerIdField);

        customerPanel.add(new JLabel("Parcel ID (Customer):"));
        parcelIdCustomerField = new JTextField();
        parcelIdCustomerField.setBorder(new EmptyBorder(5, 5, 5, 5));
        customerPanel.add(parcelIdCustomerField);

        // Panel for parcel form
        JPanel parcelPanel = new JPanel(new GridLayout(8, 1, 5, 5));  // 5px horizontal and vertical space between components
        parcelPanel.setBorder(new EmptyBorder(10, 10, 10, 10));  // Padding around parcel panel

        parcelPanel.add(new JLabel("Parcel ID:"));
        parcelIdParcelField = new JTextField();
        parcelIdParcelField.setBorder(new EmptyBorder(5, 5, 5, 5));
        parcelPanel.add(parcelIdParcelField);

        parcelPanel.add(new JLabel("Weight:"));
        weightField = new JTextField();
        weightField.setBorder(new EmptyBorder(5, 5, 5, 5));
        parcelPanel.add(weightField);

        parcelPanel.add(new JLabel("Length:"));
        lengthField = new JTextField();
        lengthField.setBorder(new EmptyBorder(5, 5, 5, 5));
        parcelPanel.add(lengthField);

        parcelPanel.add(new JLabel("Width:"));
        widthField = new JTextField();
        widthField.setBorder(new EmptyBorder(5, 5, 5, 5));
        parcelPanel.add(widthField);

        parcelPanel.add(new JLabel("Height:"));
        heightField = new JTextField();
        heightField.setBorder(new EmptyBorder(5, 5, 5, 5));
        parcelPanel.add(heightField);

        parcelPanel.add(new JLabel("Days in Depot:"));
        daysInDepotField = new JTextField();
        daysInDepotField.setBorder(new EmptyBorder(5, 5, 5, 5));
        parcelPanel.add(daysInDepotField);

        parcelPanel.add(new JLabel("Enter Parcel ID To Process Parcel :"));
        ParcelIdfield = new JTextField();
        ParcelIdfield.setBorder(new EmptyBorder(5, 5, 5, 5));
        parcelPanel.add(ParcelIdfield);

        parcelPanel.add(new JLabel("Enter Customer ID to remove customer:"));
        removecustomer = new JTextField();
        removecustomer.setBorder(new EmptyBorder(5, 5, 5, 5));
        parcelPanel.add(removecustomer);

        // Buttons
        addCustomerButton = new JButton("Add Customer");
        addCustomerButton.setBackground(Color.darkGray);
        addCustomerButton.setForeground(Color.WHITE);

        addParcelButton = new JButton("Add Parcel");
        addParcelButton.setBackground(Color.darkGray);
        addParcelButton.setForeground(Color.WHITE);

        loadParcelsButton = new JButton("Load Parcels");
        loadParcelsButton.setBackground(Color.darkGray);
        loadParcelsButton.setForeground(Color.WHITE);

        loadCustomersButton = new JButton("Customer Queue");
        loadCustomersButton.setBackground(Color.darkGray);
        loadCustomersButton.setForeground(Color.WHITE);

        ProcessButton = new JButton("Process Parcel");
        ProcessButton.setBackground(Color.darkGray);
        ProcessButton.setForeground(Color.WHITE);

        removecustomerButton = new JButton("Remove Customer");
        removecustomerButton.setBackground(Color.decode("#FF4545"));
        removecustomerButton.setForeground(Color.WHITE);

        processedParcelsButton = new JButton("Processed Parcels");
        processedParcelsButton.setBackground(Color.decode("#809D3C"));
        processedParcelsButton.setForeground(Color.WHITE);

        sortByDaysInDepotButton = new JButton("Sort by Days in Depot");
        sortByDaysInDepotButton.setBackground(Color.decode("#4DA1A9"));
        sortByDaysInDepotButton.setForeground(Color.WHITE);

        // Create a panel for the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(new EmptyBorder(10, 10, 10, 10));  // Padding around the button panel
        buttonPanel.add(addCustomerButton);
        buttonPanel.add(addParcelButton);
        buttonPanel.add(loadParcelsButton);
        buttonPanel.add(loadCustomersButton);
        buttonPanel.add(ProcessButton);
        buttonPanel.add(removecustomerButton);
        buttonPanel.add(processedParcelsButton);
        buttonPanel.add(sortByDaysInDepotButton);

        // JList to display parcels
       // parcelList = new JList<>();
        //JScrollPane parcelListScrollPane = new JScrollPane(parcelList);
        parcelList = new JList<>();
        JScrollPane parcelListScrollPane = new JScrollPane(parcelList);
        parcelList.setBackground(Color.decode("#CDC1FF")); // Set background color for the JList

        parcelListScrollPane.setPreferredSize(new Dimension(500, 0));

        
        // Add panels to frame
        frame.add(customerPanel, BorderLayout.NORTH);
        frame.add(parcelPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.add(parcelListScrollPane, BorderLayout.EAST);

        frame.setVisible(true);
    }

    // Getters for fields and buttons
    public JTextField getFirstNameField() {
        return firstNameField;
    }

    public JTextField getLastNameField() {
        return lastNameField;
    }
    public JButton getSortByDaysInDepotButton() {
        return sortByDaysInDepotButton;
    }
    public JTextField getCustomerIdField() {
        return customerIdField;
    }
    public JButton getProcessedParcelsButton() {
        return processedParcelsButton;
    }
    public JTextField getParcelIdCustomerField() {
        return parcelIdCustomerField;
    }

    public JButton getAddCustomerButton() {
        return addCustomerButton;
    }

    public JTextField getParcelIdParcelField() {
        return parcelIdParcelField;
    }

    public JTextField getWeightField() {
        return weightField;
    }

    public JTextField getLengthField() {
        return lengthField;
    }

    public JTextField getWidthField() {
        return widthField;
    }

    public JTextField getHeightField() {
        return heightField;
    }

    public JTextField getDaysInDepotField() {
        return daysInDepotField;
    }

    public JTextField getRemovecustomer() {
        return removecustomer;
    }

    public JButton getAddParcelButton() {
        return addParcelButton;
    }
    public JTextField getParcelIdField() {
        return ParcelIdfield;
    }

    public JButton getLoadCustomersButton() {
        return loadCustomersButton;
    }

    public JButton getLoadParcelsButton() {
        return loadParcelsButton;
    }

    public JButton getProcessButton() {
        return ProcessButton;
    }

    public JButton getRemoveCustomerButton() {
        return removecustomerButton;
    }

    // Method to set the data for the JList (parcel data)
    public void setParcelListData(List<String> parcels) {
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (String parcel : parcels) {
            listModel.addElement(parcel);
        }
        parcelList.setModel(listModel);
    }

    // Method to set the data for the JList (customer data)
    public void setCustomerListData(List<Customer> customers) {
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (Customer customer : customers) {
            listModel.addElement(customer.toString()); // Use Customer's toString method
        }
        customerList.setModel(listModel);
    }

    // Method to set the data for the JList (process data)
    public void setProcessListData(List<String> processparcels) {
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (String processparcel : processparcels) {
            listModel.addElement(processparcel);
        }
        parcelList.setModel(listModel);
    }

    // Method to get the frame (for passing to Manager)
    public JFrame getFrame() {
        return frame;
    }

    // Clear input fields after adding customer or parcel
    public void clearInputFields() {
        firstNameField.setText("");
        lastNameField.setText("");
        customerIdField.setText("");
        parcelIdCustomerField.setText(""); // Clear customer Parcel ID
        parcelIdParcelField.setText("");
}}