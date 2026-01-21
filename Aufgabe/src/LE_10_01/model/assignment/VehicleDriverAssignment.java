package LE_10_01.model.assignment;

import LE_10_01.model.person.Employee;
import LE_10_01.model.vehicle.Vehicle;

import java.time.LocalDate;
import java.util.Objects;

public class VehicleDriverAssignment {

    private final Vehicle vehicle;
    private final Employee driver;
    private final LocalDate assignedFrom;
    private LocalDate assignedTo; // null = current

    public VehicleDriverAssignment(Vehicle vehicle, Employee driver, LocalDate assignedFrom, LocalDate assignedTo) {
        this.vehicle = Objects.requireNonNull(vehicle, "vehicle must not be null");
        this.driver = Objects.requireNonNull(driver, "driver must not be null");
        this.assignedFrom = Objects.requireNonNull(assignedFrom, "assignedFrom must not be null");
        this.assignedTo = assignedTo;
    }

    public Vehicle getVehicle() { return vehicle; }
    public Employee getDriver() { return driver; }
    public LocalDate getAssignedFrom() { return assignedFrom; }
    public LocalDate getAssignedTo() { return assignedTo; }

    public boolean isCurrent() { return assignedTo == null; }

    public void close(LocalDate endDate) { this.assignedTo = endDate; }

    @Override
    public String toString() {
        return "Assignment{" +
                "vehicle=" + vehicle.getLicensePlate() +
                ", driver=" + driver.getName() +
                ", from=" + assignedFrom +
                ", to=" + assignedTo +
                '}';
    }
}
