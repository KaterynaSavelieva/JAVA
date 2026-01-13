package LE_10_01.model.vehicle;

public abstract class Vehicle {

    private int vehicleId;
    private VehicleType vehicleType; //Enum: CAR, TRUCK, MOTORCYCLE

    private String licensePlate;
    private String brand;
    private int productionYear;

    private EnergyType energyType; //Enum: PETROL, DIESEL, ELECTRIC, HYBRID
    private EnergyUnit energyUnit; //Enum: LITER, KWH

    private double energyConsumption; // per 100 km
    private int mileage;            //пробіг
    private double energyLevel;     //поточний рівень енергії

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
        this.energyConsumption = energyConsumption;
        this.mileage = Math.max(mileage, 0);
        this.energyLevel = Math.max(energyLevel, 0);
    }

    public void drive(int km) { //збільшує пробіг автомобіля
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
