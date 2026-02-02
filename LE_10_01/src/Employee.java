public class Employee {
    private int id;
    private String name;
    private String drivingLicenseType;

    public Employee(int id, String name, String drivingLicenseType){
        this.id = id;
        this.name = name;
        this.drivingLicenseType = drivingLicenseType;
    }

    public String getName(){
        return name;
    }
}
