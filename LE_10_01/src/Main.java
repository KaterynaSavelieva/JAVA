public class Main {
    public static void main(String[] args) {

        Employee emp1 = new Employee(1, "John Smith", "B");
        Car car1 =new Car(101, "AT-1234", "Toyota", 5);
        car1.setCurrentDriver(emp1);

        car1.refeul(40);
        car1.drive(100);

        System.out.println("Driver: " + car1.getCurrentDriver().getName());

    }
}
