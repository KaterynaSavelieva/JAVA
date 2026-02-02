package LE_11_01.model;

import java.time.LocalDate;

public class Coach extends Person {
    private final LicenseLevel licenseLevel;


    public Coach (int personId, String name, String email, String phone,
                  LocalDate birthdate,  LicenseLevel licenseLevel) {
        super(personId, name, email, phone, birthdate, Role.COACH);
        this.licenseLevel = licenseLevel;

    }
    public LicenseLevel getLicenseLevel() {return licenseLevel; }






}
