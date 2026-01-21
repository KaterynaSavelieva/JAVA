package LE_10_01.model.person;

import java.time.LocalDate;
import java.util.Objects;

public class Person {
    private final int personId;
    private final String name;
    private final String email;
    private final String phone;
    private final LocalDate birthdate; // can be null

    protected Person(int personId, String name, String email, String phone, LocalDate birthdate) {
        this.personId = personId;
        this.name = Objects.requireNonNull(name, "name must not be null");
        this.email = Objects.requireNonNull(email, "email must not be null");
        this.phone = phone;
        this.birthdate = birthdate; // allow null
    }

    public int getPersonId() { return personId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public LocalDate getBirthdate() { return birthdate; }

    @Override
    public String toString() {
        return "Person{" +
                "personId=" + personId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
