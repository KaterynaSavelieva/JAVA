package LE_10_01.model;


public class License {
    private final LicenseCode code;     // DB: license.license_code
    private final String description;   // DB: license.description

    public License(LicenseCode code, String description) {
        this.code = code;
        this.description = description;
    }

    public LicenseCode getCode() {return code;}
    public String getDescription() {return description;}

    @Override
    public String toString() {
        return code + (description != null ? " (" + description + ")" : "");
    }
}
