package LE_10_01.app;

import LE_10_01.model.*;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        Employee anna = new Employee(
                1, 1,
                "Anna Berger", "anna.berger@example.com",
                "+43 660 111 222", LocalDate.of(1991, 4, 12)
        );
        anna.addLicense(LicenseCode.B);

        Car toyota = new Car(1,"MT-AB123","Toyota",2019,
                EnergyType.PETROL, EnergyUnit.LITER,
                6.80,84500,
                22.50,
                5
        );


        VehicleDriverAssignment a1 =
                new VehicleDriverAssignment(toyota, anna, LocalDate.of(2024, 1, 10), null);

        System.out.println(anna);
        System.out.println(toyota);
        System.out.println(a1);
        System.out.println("Current: " + a1.isCurrent());
    }
}
