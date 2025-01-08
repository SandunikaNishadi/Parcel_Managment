package Parcel_Model;

import java.util.*;

public class QueueOfCustomers {
    private List<Customer> customers;

    public QueueOfCustomers() {
        customers = new ArrayList<>();
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public Customer findCustomerByName(String name) {
        for (Customer customer : customers) {
            if (customer.getName().equalsIgnoreCase(name)) {
                return customer;
            }
        }
        return null;
    }
}
