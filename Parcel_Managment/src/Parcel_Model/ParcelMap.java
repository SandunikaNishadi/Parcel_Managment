package Parcel_Model;

import java.util.HashMap;

import java.util.*;

public class ParcelMap {
    private Map<String, Parcel> parcelMap;

    public ParcelMap() {
        parcelMap = new HashMap<>();
    }

    public void addParcel(Parcel parcel) {
        parcelMap.put(parcel.getParcelId(), parcel);
    }

    public Collection<Parcel> getParcels() {
        return parcelMap.values();
    }
}
