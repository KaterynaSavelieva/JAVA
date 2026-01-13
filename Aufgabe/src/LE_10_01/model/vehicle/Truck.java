package LE_10_01.model.vehicle;

public class Truck extends Vehicle {

    private final double  maxLoadKg;

    public Truck(int vehicleId,
                 String licensePlate,
                 String brand,
                 int productionYear,
                 EnergyType energyType,
                 EnergyUnit energyUnit,
                 double  energyConsumption,
                 int mileage,
                 double  energyLevel,
                 double  maxLoadKg) {

        super(vehicleId, VehicleType.TRUCK, licensePlate, brand, productionYear,
                energyType, energyUnit, energyConsumption, mileage, energyLevel);

        this.maxLoadKg = maxLoadKg;
    }

    public double  getMaxLoadKg() {return maxLoadKg;}


}
