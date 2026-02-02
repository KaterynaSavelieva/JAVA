package LE_11_01.model;

import java.time.LocalDate;
import java.util.Objects;

public class Member extends Person {
    private final LocalDate joinDate;

    public Member (int personId, String name, String email, String phone,
                   LocalDate birthdate, LocalDate joinDate) {
        super(personId, name, email, phone, birthdate, Role.MEMBER);
        this.joinDate = Objects.requireNonNull(joinDate, "joinDate must not be null");
        if (joinDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Join date cannot be in the future");
        }
    }

    public LocalDate getJoinDate() {return joinDate;}

    @Override
    public String toString() {
        return "[MEMBER] " + super.toString() + ", joinDate=" + joinDate;
    }


}


