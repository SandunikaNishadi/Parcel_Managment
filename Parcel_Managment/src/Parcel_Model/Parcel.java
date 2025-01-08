package Parcel_Model;

public class Parcel {
    private int parcelId;
    private double weight;
    private double length;
    private double width;
    private double height;
    private int daysInDepot;

    public Parcel(int parcelId, double weight, double length, double width, double height, int daysInDepot) {
        this.parcelId = parcelId;
        this.weight = weight;
        this.length = length;
        this.width = width;
        this.height = height;
        this.daysInDepot = daysInDepot;
    }

    public int getParcelId() {
        return parcelId;
    }

    public double getWeight() {
        return weight;
    }

    public double getLength() {
        return length;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public int getDaysInDepot() {
        return daysInDepot;
    }

    @Override
    public String toString() {
        return "Parcel ID: " + parcelId + ", Weight: " + weight + ", Dimensions: " + length + "x" + width + "x" + height
                + ", Days in Depot: " + daysInDepot;
    }

	public void setParcelId(int parcelId) {
		this.parcelId = parcelId;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public void setDaysInDepot(int daysInDepot) {
		this.daysInDepot = daysInDepot;
	}

	
}