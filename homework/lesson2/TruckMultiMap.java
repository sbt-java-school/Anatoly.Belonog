package homework;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Anatoly on 17.08.2016.
 */
public class TruckMultiMap implements MultiMap<String, Truck> {
    HashMap<String, List<Truck>> truckMap = new HashMap<>();

    public TruckMultiMap() {}

    public TruckMultiMap(HashMap<String, List<Truck>> truckMap) {
        this.truckMap = truckMap;
    }

    @Override
    public void put(String type, Truck truck) {
        truckMap.get(type).add(truck);
    }

    public void put(String type, List<Truck> listTruck) {
        truckMap.put(type, listTruck);
    }

    @Override
    public List<Truck> get(String type) {
        return truckMap.get(type);
    }

    public void printEveryKey() {
        for (Map.Entry<String, List<Truck>> entry : truckMap.entrySet())
            System.out.println(entry.getKey());
    }
}
