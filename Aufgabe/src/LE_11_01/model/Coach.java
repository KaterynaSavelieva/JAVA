package LE_09_02.model;

public class Coach extends Person{
    private String licenseLevel;

    public Coach(int id,
                 String firstName,
                 String lastName,
                 String email,
                 String phone,
                 String licenseLevel) {
        super(id, firstName, lastName, email, phone);
        this.licenseLevel = licenseLevel;
    }

    public String getLicenseLevel() {return licenseLevel;}

    //Member і Coach успадковують всі public (і protected) методи Person


}
