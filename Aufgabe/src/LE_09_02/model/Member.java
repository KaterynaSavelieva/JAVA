package LE_09_02.model;

import java.time.LocalDate;

public class Member extends Person { //extends → наслідування
    private String memberNumber;
    private LocalDate joinDate;

    public Member(int id,
                  String firstName,
                  String lastName,
                  String email,
                  String phone,
                  String memberNumber,
                  LocalDate joinDate) {
        super(id, firstName, lastName, email, phone); //Створи частину Person всередині Member
        // super(...) → виклик конструктора батьківського класу
        // Java робить так:
        // спочатку створює Person
        // потім додає Member-частину
        // Без super(...) — код НЕ скомпілюється.
        // Member НЕ повторює поля Person
        //Member має доступ до PUBLIC методів Person -УСПАДКОВУЄ

        this.memberNumber = memberNumber;
        this.joinDate = joinDate;
    }

    public String getMemberNumber() {
        return memberNumber;
    }

    public LocalDate getJoinDate() {
        return joinDate;
    }
}


