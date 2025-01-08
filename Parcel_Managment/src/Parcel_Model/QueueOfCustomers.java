package Parcel_Model;

import java.util.*;

public class QueueOfCustomers {
    private List<Customer> customers;

    public QueueOfCustomers() {
        customers = new ArrayList<>();
    }

    // Add a customer to the queue
    public void enqueue(Customer customer) {
        customers.add(customer);
    }

    // Get all customers in the queue
    public List<Customer> getAllCustomers() {
        return customers;
    }

    
    
}
