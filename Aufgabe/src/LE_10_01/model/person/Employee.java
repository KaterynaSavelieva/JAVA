package LE_10_01.model.person;

import LE_10_01.model.license.LicenseCode;

import java.time.LocalDate;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;

public class Employee extends Person {

    private final int employeeId;
    private final EnumSet<LicenseCode> licenses = EnumSet.noneOf(LicenseCode.class);

    public Employee(int personId, int employeeId, String name, String email, String phone, LocalDate birthdate) {
        super(personId, name, email, phone, birthdate);
        this.employeeId = employeeId;
    }

    public int getEmployeeId() { return employeeId; }

    public void addLicense(LicenseCode code) {
        licenses.add(Objects.requireNonNull(code, "license code must not be null"));
    }

    public void removeLicense(LicenseCode code) {
        licenses.remove(code);
    }

    public Set<LicenseCode> getLicenses() {
        return Collections.unmodifiableSet(licenses);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", personId=" + getPersonId() +
                ", name='" + getName() + '\'' +
                ", licenses=" + licenses +
                '}';
    }
}
