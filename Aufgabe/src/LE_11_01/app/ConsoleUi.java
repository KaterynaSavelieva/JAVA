package LE_11_01.app;

import LE_11_01.management.Management;
import LE_11_01.model.Coach;
import LE_11_01.model.LicenseLevel;
import LE_11_01.model.TeamCategory;

import java.time.LocalDate;
import java.util.Scanner;

public class ConsoleUi {
    private final Scanner scanner;
    private final Management management;
    private record PersonData (String name, String email, String phone, LocalDate  birthDate) {}

    public ConsoleUi(Scanner scanner, Management management) {
        this.scanner = scanner;
        this.management = management;
    }

    public void run() {
        while (true) {
            MenuPrinter.printMenu();
            int choice = InputHelper.readNonNegativeInt(scanner, "Choose an option: ");
            switch (choice) {
                case 1: createMember(); InputHelper.pressEnter(scanner);break;
                case 2:
                    management.printAllMembers();
                    InputHelper.pressEnter(scanner);
                    break;
                case 3: createCoach(); InputHelper.pressEnter(scanner);break;
                case 4:
                    management.printAllCoaches();
                    InputHelper.pressEnter(scanner);
                    break;
                case 5: createTeam(); InputHelper.pressEnter(scanner);break;
                case 6:
                    management.printAllTeams();
                    InputHelper.pressEnter(scanner);
                    break;
                case 7: addMemberToTeam(); InputHelper.pressEnter(scanner);break;
                case 8: printTeamDetails(); InputHelper.pressEnter(scanner);break;
                case 9: removeMemberFromTeam();InputHelper.pressEnter(scanner);break;
                case 0: System.out.println("0: Quit"); return;
                default: System.out.println("Invalid choice");

            }
        }
    }

    private PersonData readPersonData() {
        String name = InputHelper.readNotBlank(scanner, "Name: ");
        String email = InputHelper.readEmail(scanner, "Email: ");
        String phone = InputHelper.readOptional(scanner, "Phone: ");
        LocalDate birthDate = InputHelper.readDate(scanner, "Birth date (yyyy-mm-dd): ");
        return new PersonData(name, email, phone, birthDate);
    }

    private void createMember() {
        PersonData p  = readPersonData();
        LocalDate joiningDate = InputHelper.readDate(scanner, "Join date: ");
        management.createMember(p.name(), p.email(), p.phone(), p.birthDate(), joiningDate);
        System.out.println("Member added successfully");
    }

    private void createCoach() {
        PersonData p = readPersonData();
        String licenseLevel = InputHelper.readEnum(scanner,
                "License level (A/B/C/D/E): ",
                new String []{"A","B","C","D","E"});
    }

    private void createTeam() {
        String teamName = InputHelper.readNotBlank(scanner, "Team name: ");
        String categoryStr  = InputHelper.readEnum(scanner,
                "Team category (U8/U10/U12/U18/ADULT): ",
                new String[]{"U8", "U10", "U12", "U18", "ADULT"});
        TeamCategory teamCategory = TeamCategory.valueOf(categoryStr);

        System.out.println("Available coaches: ");
        management.printAllCoaches();

        Coach coach;
        while (true) {
            int coachId =InputHelper.readPositiveInt(scanner, "Coach ID: ");
            coach = management.findCoachById(coachId);
            if (coach == null) {
                System.out.println("Coach with id " + coachId + " not found");
                continue;
            }
            break;
        }
        management.createTeam(teamName, coach, teamCategory);
        System.out.println("Team created successfully");

    }

    private void addMemberToTeam() {
        System.out.println("Available teams: ");
        management.printAllTeams();
        int teamId = InputHelper.readPositiveInt(scanner, "Team ID: ");

        System.out.println("Available members: ");
        management.printAllMembers();
        int memberId = InputHelper.readPositiveInt(scanner, "Member ID: ");

        try {
            management.addMemberToTeam(teamId, memberId);
        }catch (IllegalArgumentException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    private void removeMemberFromTeam() {
        System.out.println("Available teams: ");
        management.printAllTeams();
        int teamId = InputHelper.readPositiveInt(scanner, "Team ID: ");

        while (true) {
            System.out.println("Available members: ");
            management.printTeamDetails(teamId);
            int memberId = InputHelper.readNonNegativeInt(scanner, "Member ID (0 to exit): ");

            if (memberId==0) return;

            try {
                management.removeMemberFromTeam(teamId, memberId);
                System.out.println("Member removed successfully");
                break;
            }catch (IllegalArgumentException ex) {
                System.out.println("Error: " + ex.getMessage());
                System.out.println("Please try again");
            }

        }

    }

    private void printTeamDetails() {
        System.out.println("Available teams: ");
        management.printAllTeams();

        int teamId = InputHelper.readPositiveInt(scanner, "Team ID: ");
        management.printTeamDetails(teamId);
    }



}
