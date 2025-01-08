package Parcel_Model;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParcelMap {
    private Map<Integer, Parcel> parcelMap;

    public ParcelMap() {
        parcelMap = new HashMap<>();
    }

    public void addParcel(int id, Parcel parcel) {
        parcelMap.put(id, parcel);
    }

    public List<Parcel> getAllParcels() {
        return List.copyOf(parcelMap.values());
    }
}