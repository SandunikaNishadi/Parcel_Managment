package Parcel_View;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class View {

    private JFrame frame;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField customerIdField;
    
    private JList<String> customerList; // JList for customers
    private JButton addCustomerButton;
    
    public View() {
        // Initialize JFrame
        frame = new JFrame("Parcel Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(Color.decode("#DEAA79"));

        // Panel for customer form
        JPanel customerPanel = new JPanel(new GridLayout(5, 2));
        customerPanel.add(new JLabel("First Name:"));
        firstNameField = new JTextField();
        customerPanel.add(firstNameField);

        customerPanel.add(new JLabel("Last Name:"));
        lastNameField = new JTextField();
        customerPanel.add(lastNameField);

        customerPanel.add(new JLabel("Customer ID:"));
        customerIdField = new JTextField();
        customerPanel.add(customerIdField);
        
        // Buttons
        addCustomerButton = new JButton("Add Customer");
        
        // Create a panel for the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addCustomerButton);
        

        // JList to display parcels
       

        // Add panels to frame
        frame.add(customerPanel, BorderLayout.NORTH);
       
        frame.add(buttonPanel, BorderLayout.SOUTH);
       

        frame.setVisible(true);
    }

    // Getters for fields and buttons
    public JTextField getFirstNameField() {
        return firstNameField;
    }

    public JTextField getLastNameField() {
        return lastNameField;
    }
   
    public JTextField getCustomerIdField() {
        return customerIdField;
    }
   
    public JButton getAddCustomerButton() {
        return addCustomerButton;
    }
     
    // Method to set the data for the JList (customer data)
    public void setCustomerListData(List<Customer> customers) {
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (Customer customer : customers) {
            listModel.addElement(customer.toString()); // Use Customer's toString method
        }
        customerList.setModel(listModel);
    }

    // Clear input fields after adding customer or parcel
    public void clearInputFields() {
        firstNameField.setText("");
        lastNameField.setText("");
        customerIdField.setText("");
        
    }
   
}