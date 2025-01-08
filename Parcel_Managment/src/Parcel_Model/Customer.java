package Parcel_Model;


public class Customer {

	private String customerId;
    private String firstName;
    private String lastName;
    private String parcelId; // Added parcelId field

    // Constructor
    public Customer(String customerId, String firstName, String lastName, String parcelId) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.parcelId = parcelId; // Initialize parcelId
    }

    // Getter methods
    public String getCustomerId() {
        return customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getParcelId() {
        return parcelId;
    }

    // Setter methods (optional, depending on requirements)
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setParcelId(String parcelId) {
        this.parcelId = parcelId;
    }

    @Override
    public String toString() {
        return "Customer ID: " + customerId + ", Name: " + firstName + " " + lastName;
    }
}