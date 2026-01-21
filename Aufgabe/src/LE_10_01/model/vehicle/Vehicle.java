package LE_10_01.model.vehicle;

public abstract class Vehicle {

    private final int vehicleId;
    private final VehicleType vehicleType;

    private final String licensePlate;
    private final String brand;
    private final int productionYear;

    private final EnergyType energyType;
    private final EnergyUnit energyUnit;

    private final double energyConsumption; // per 100 km
    private int mileage;
    private double energyLevel;

    protected Vehicle(int vehicleId,
                      VehicleType vehicleType,
                      String licensePlate,
                      String brand,
                      int productionYear,
                      EnergyType energyType,
                      EnergyUnit energyUnit,
                      double energyConsumption,
                      int mileage,
                      double energyLevel) {

        this.vehicleId = vehicleId;
        this.vehicleType = vehicleType;
        this.licensePlate = licensePlate;
        this.brand = brand;
        this.productionYear = productionYear;
        this.energyType = energyType;
        this.energyUnit = energyUnit;
        this.energyConsumption = Math.max(energyConsumption, 0);
        this.mileage = Math.max(mileage, 0);
        this.energyLevel = Math.max(energyLevel, 0);
    }

    public void drive(int km) {
        if (km > 0) mileage += km;
    }

    public void refuelOrCharge(double amount) {
        if (amount > 0) energyLevel += amount;
    }

    public int getVehicleId() { return vehicleId; }
    public VehicleType getVehicleType() { return vehicleType; }
    public String getLicensePlate() { return licensePlate; }
    public String getBrand() { return brand; }
    public int getProductionYear() { return productionYear; }
    public EnergyType getEnergyType() { return energyType; }
    public EnergyUnit getEnergyUnit() { return energyUnit; }
    public double getEnergyConsumption() { return energyConsumption; }
    public int getMileage() { return mileage; }
    public double getEnergyLevel() { return energyLevel; }

    @Override
    public String toString() {
        return vehicleType + "{" +
                "id=" + vehicleId +
                ", plate='" + licensePlate + '\'' +
                ", brand='" + brand + '\'' +
                ", year=" + productionYear +
                ", energyType=" + energyType +
                ", unit=" + energyUnit +
                ", consumption=" + energyConsumption +
                ", mileage=" + mileage +
                ", level=" + energyLevel +
                '}';
    }
}
