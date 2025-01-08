package Parcel_Model;

public class Parcel {
    private String parcelId;
    private Customer customer;

    public Parcel(String parcelId, Customer customer) {
        this.parcelId = parcelId;
        this.customer = customer;
    }

    public String getParcelId() {
        return parcelId;
    }

    public Customer getCustomer() {
        return customer;
    }
}
