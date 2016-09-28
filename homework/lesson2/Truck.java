package homework;

import com.sun.istack.internal.NotNull;

import java.util.Iterator;
import java.util.List;

public class Truck implements Iterable<Truck.CargoObject> {
    private List<CargoObject> cargo;
    private int maxWeight;

    public Truck() {
        cargo = null;
    }

    public Truck(List<CargoObject> cargo) {
        this.cargo = cargo;
    }

    class CargoObject {
        private int weight;

        public CargoObject() {

        }

        public CargoObject(int weight) {
            this.weight = weight;
        }

        public int getWeight() {
            return this.weight;
        }
    }

    public CargoObject createCargoObject() {
        return new CargoObject();
    }

    @NotNull
    private Iterator<CargoObject> getCargo() {
        return cargo.iterator();
    }

    @Override
    public Iterator<CargoObject> iterator() {
        return getCargo();
    }

    public int weight() {
        int res = 0;
        for (Truck.CargoObject obj: this) {
            res+= obj.getWeight();
        }
        return res;
    }

    public void addObject(Truck.CargoObject obj) {
        if (weight() + obj.getWeight() <= maxWeight)
            cargo.add(obj);
    }
}

