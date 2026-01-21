package LE_10_01.model.vehicle;

public class Car extends Vehicle {
    private final int numberOfSeats;

    public Car(int vehicleId,
               String licensePlate,
               String brand,
               int productionYear,
               EnergyType energyType,
               EnergyUnit energyUnit,
               double energyConsumption,
               int mileage,
               double energyLevel,
               int numberOfSeats) {

        super(vehicleId, VehicleType.CAR, licensePlate, brand, productionYear,
                energyType, energyUnit, energyConsumption, mileage, energyLevel);

        this.numberOfSeats = numberOfSeats;
    }

    public int getNumberOfSeats() { return numberOfSeats; }
}
