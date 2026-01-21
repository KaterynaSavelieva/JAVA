package LE_10_01.model.vehicle;

public class Motorcycle extends Vehicle {
    private final int engineCapacityCcm;

    public Motorcycle(int vehicleId,
                      String licensePlate,
                      String brand,
                      int productionYear,
                      EnergyType energyType,
                      EnergyUnit energyUnit,
                      double energyConsumption,
                      int mileage,
                      double energyLevel,
                      int engineCapacityCcm) {

        super(vehicleId, VehicleType.MOTORCYCLE, licensePlate, brand, productionYear,
                energyType, energyUnit, energyConsumption, mileage, energyLevel);

        this.engineCapacityCcm = engineCapacityCcm;
    }

    public int getEngineCapacityCcm() { return engineCapacityCcm; }
}
