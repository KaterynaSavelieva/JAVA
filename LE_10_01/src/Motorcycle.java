public class Motorcycle extends Vehicle{
    private int engineCapacityCcm; //об’єм двигуна (см³)

    public Motorcycle(int id, String licensePlate, String brand, int engineCapacityCcm){
        super(id, licensePlate, brand);
        this.engineCapacityCcm = engineCapacityCcm;
    }
}
