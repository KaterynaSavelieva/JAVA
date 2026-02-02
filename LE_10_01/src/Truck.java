public class Truck extends Vehicle {
    private double maxLoadKg;

    public Truck(int id, String licensePlate, String brand, double maxLoadKg){
        super(id, licensePlate, brand);
        this.maxLoadKg = maxLoadKg;
    }
}
