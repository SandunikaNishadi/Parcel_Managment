package Parcel_Model;

import java.util.*;

public class Manager {
    private QueueOfCustomers customerQueue;
    private ParcelMap parcelMap;

    public Manager() {
        customerQueue = new QueueOfCustomers();
        parcelMap = new ParcelMap();
    }

    public void addCustomer(String name, String address) {
        Customer customer = new Customer(name, address);
        customerQueue.addCustomer(customer);
    }

    public void addParcel(String parcelId, String customerName) {
        Customer customer = customerQueue.findCustomerByName(customerName);
        if (customer != null) {
            Parcel parcel = new Parcel(parcelId, customer);
            parcelMap.addParcel(parcel);
        }
    }

    public void processParcels() {
        for (Parcel parcel : parcelMap.getParcels()) {
            System.out.println("Processing parcel: " + parcel.getParcelId());
        }
    }
}
