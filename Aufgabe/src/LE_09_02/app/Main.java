package LE_09_02.app;

import LE_09_02.model.Club;
import LE_09_02.model.Coach;
import LE_09_02.model.Member;
import LE_09_02.model.Team;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        Coach coach1 = new Coach (
                1,
                "Anna",
                "Mayer",
                "anna@mail.at",
                "+43-677-22",
                "B-License"
        );

        Member m1 = new Member(
                10,
                "Max",
                "Huber",
                "max@mail.at",
                "43-677-212",
                "M-110",
                LocalDate.of(2024, 1, 1)
        );

        Member m2 = new Member(
                11,
                "Lena",
                "Fischer",
                "lena@mail.at",
                "+43 333",
                "M-002",
                LocalDate.of(2024, 2, 5)
        );

        Team team1 = new Team(
                100,
                "U12 Tigers",
                "U12",
                coach1

        );

        team1.addMember(m1);
        team1.addMember(m2);

        Club club1 = new Club("Green Sports Club");

        club1.addTeam(team1);

        System.out.println("Club 1: " + club1.getName());
        System.out.println("Team 1: " + team1.getName() + ", total members: " + team1.getMembers().size());
        System.out.println("Coach1: "+ team1.getCoach().getFirstName() + " " + team1.getCoach().getLastName());

        System.out.println("Members: ");
        for (Member m: team1.getMembers()) {
            System.out.println("\t" + m.getFirstName() + " " + m.getLastName());
        }
    }
}
