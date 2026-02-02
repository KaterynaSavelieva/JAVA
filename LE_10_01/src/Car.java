public class Car extends Vehicle {
    private int numberOfSeats;

    public Car (int id, String licensePlate, String brand, int numberOfSeats){
        super(id, licensePlate, brand);
        this.numberOfSeats = numberOfSeats;
    }
}
