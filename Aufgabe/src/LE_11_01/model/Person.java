package LE_11_01.model;

import java.time.LocalDate;
import java.util.Objects;

public abstract class Person {
    private final int personId;
    private final String name;
    private final String email;
    private final String phone;
    private final LocalDate birthdate;
    private final Role role;

    protected Person(int personId, String name, String email, String phone, LocalDate birthdate, Role role) {


        this.personId = personId;
        this.name = Objects.requireNonNull(name, "name must not be null");
        this.email = Objects.requireNonNull(email, "email must not be null");
        this.phone =phone;
        this.birthdate = Objects.requireNonNull(birthdate, "birthdate must not be null");
        if (birthdate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("The birthdate cannot be in the future.");
        }
        this.role = Objects.requireNonNull(role, "role must not be null");
    }

    public int getPersonId() {return personId;}
    public String getName() {return name;}
    public String getEmail() {return email;}
    public String getPhone() {return phone;}
    public LocalDate getBirthdate() {return birthdate;}
    public Role getRole() {return role;}

    @Override
    public String toString() {
        return "Person{" +
                "personId=" + personId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

}
