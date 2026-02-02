public abstract class Vehicle {
    protected int id;
    protected String licensePlate;
    protected String brand;
    protected int mileage; //пробіг (км)
    protected double fuelLiters; //кількість пального
    protected Employee currentDriver;

    public Vehicle(int id, String licensePlate, String brand) {
        //Only essential attributes are passed to the constructor.
        //Mileage, fuel and driver are initialized with default values
        //and changed later during the vehicle lifecycle.
        this.id = id;
        this.licensePlate = licensePlate;
        this.brand = brand;
        this.mileage = 0;
        this.fuelLiters = 0.0;
    }

    public void  drive (int km) {
        mileage +=km;
        fuelLiters -=km*0.1;
    }

    public void refeul (double liters) {
        fuelLiters += liters;

    }

    public void setCurrentDriver (Employee driver) {
        this.currentDriver = driver;
    }

    public Employee getCurrentDriver(){
        return currentDriver;
    }

}
