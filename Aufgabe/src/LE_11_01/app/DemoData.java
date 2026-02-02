package LE_11_01.app;

import LE_11_01.management.Management;
import LE_11_01.model.*;

import  java.time.LocalDate;

public class DemoData {

    private DemoData() {}

    public static Management create() {

        Management management = new Management();

        // ===== MEMBERS (12) =====
        management.createMember("Anna Keller", "anna@mail.com", "111-111",
                LocalDate.of(1998, 5, 10), LocalDate.of(2023, 9, 1));
        management.createMember("Tom Fischer", "tom@mail.com", "222-222",
                LocalDate.of(1997, 3, 20), LocalDate.of(2023, 9, 1));
        management.createMember("Lisa Berger", "lisa@mail.com", null,
                LocalDate.of(2000, 12, 1), LocalDate.of(2023, 9, 1));
        management.createMember("Paul Maier", "paul@mail.com", "333-333",
                LocalDate.of(1999, 7, 14), LocalDate.of(2023, 9, 1));

        management.createMember("Julia Wolf", "julia@mail.com", "444-444",
                LocalDate.of(2001, 1, 5), LocalDate.of(2024, 1, 10));
        management.createMember("David Braun", "david@mail.com", null,
                LocalDate.of(1998, 11, 30), LocalDate.of(2024, 1, 10));
        management.createMember("Sophie Lang", "sophie@mail.com", "555-555",
                LocalDate.of(2002, 6, 18), LocalDate.of(2024, 1, 10));
        management.createMember("Mark Steiner", "mark@mail.com", "666-666",
                LocalDate.of(1997, 9, 9), LocalDate.of(2024, 1, 10));

        management.createMember("Nina Schmid", "nina@mail.com", null,
                LocalDate.of(2000, 4, 2), LocalDate.of(2024, 2, 5));
        management.createMember("Lucas Hofer", "lucas@mail.com", "777-777",
                LocalDate.of(1999, 8, 22), LocalDate.of(2024, 2, 5));
        management.createMember("Eva Leitner", "eva@mail.com", "888-888",
                LocalDate.of(2001, 10, 11), LocalDate.of(2024, 2, 5));
        management.createMember("Martin Pichler", "martin@mail.com", null,
                LocalDate.of(1996, 2, 27), LocalDate.of(2024, 2, 5));

        // ===== COACHES (5) =====
        management.createCoach("Michael Bauer", "m.bauer@mail.com", "555-101",
                LocalDate.of(1980, 3, 12), LicenseLevel.A);
        management.createCoach("Sabine Koch", "s.koch@mail.com", "555-102",
                LocalDate.of(1985, 7, 8), LicenseLevel.B);
        management.createCoach("Thomas Wagner", "t.wagner@mail.com", null,
                LocalDate.of(1978, 11, 19), LicenseLevel.A);
        management.createCoach("Peter Huber", "p.huber@mail.com", "555-103",
                LocalDate.of(1982, 5, 30), LicenseLevel.C);
        management.createCoach("Claudia Gruber", "c.gruber@mail.com", "555-104",
                LocalDate.of(1987, 9, 14), LicenseLevel.B);

        // ===== TEAMS (3) =====
        management.createTeam("Junior Tigers", management.findCoachById(13), TeamCategory.U10);
        management.createTeam("Blue Eagles", management.findCoachById(14), TeamCategory.U12);
        management.createTeam("Red Lions", management.findCoachById(15), TeamCategory.U18);

        // ===== ADD MEMBERS TO TEAMS =====
        // Team 1 (U10) → members 1–4
        management.addMemberToTeam(1, 1);
        management.addMemberToTeam(1, 2);
        management.addMemberToTeam(1, 3);
        management.addMemberToTeam(1, 4);

        // Team 2 (U12) → members 5–8
        management.addMemberToTeam(2, 5);
        management.addMemberToTeam(2, 6);
        management.addMemberToTeam(2, 7);
        management.addMemberToTeam(2, 8);

        // Team 3 (U18) → members 9–12
        management.addMemberToTeam(3, 9);
        management.addMemberToTeam(3, 10);
        management.addMemberToTeam(3, 11);
        management.addMemberToTeam(3, 12);

        return management;
    }
}


