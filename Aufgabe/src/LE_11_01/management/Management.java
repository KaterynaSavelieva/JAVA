package LE_11_01.management;

import java.time.LocalDate;

import LE_11_01.app.InputHelper;
import LE_11_01.app.MenuPrinter;
import LE_11_01.model.*;

import java.util.*;


public class Management {

    private final List<Club> clubs = new ArrayList<>();
    private final List<Team> teams = new ArrayList<>();
    private final Map<Integer, Member> members = new HashMap<>(); //Map to store members by their ID, because it guarantees uniqueness and allows fast access
    private final Map<Integer, Coach> coaches = new HashMap<>();

    private int nextClubId = 1;
    private int nextTeamId = 1;
    private int nextPersonId = 1;

    public int getNextClubId() { return nextClubId++; }
    public int getNextTeamId() { return nextTeamId++; }
    public int getNextPersonId() { return nextPersonId++; }

    public void createMember (String name, String email, String phone,
                              LocalDate birthDate, LocalDate joinDate) {
        int personId = getNextPersonId();
        Member member = new Member(personId, name, email, phone, birthDate, joinDate);
        members.put(personId, member);
        System.out.println("Member created: " + member);
    }

    public void createCoach (String name, String email, String phone,
                             LocalDate birthDate, LicenseLevel licenseLevel) {
        int personId = getNextPersonId();
        Coach coach = new Coach(personId, name, email, phone, birthDate, licenseLevel);
        coaches.put(personId, coach);
        System.out.println("Coach created: " + coach);

    }

    public void createTeam (String nameTeam, Coach coach, TeamCategory teamCategory) {
        int teamId = getNextTeamId();
        Team team = new Team(teamId, nameTeam, coach, teamCategory);
        teams.add(team);
        System.out.println("Team created: " + team.getNameTeam()+ " (id=" + teamId + ")");
    }

    public Coach findCoachById(int coachId) {return coaches.get(coachId); }
    public Collection<Coach> getAllCoaches() {
        return Collections.unmodifiableCollection(coaches.values());
    }

    public Member findMemberById(int memberId) { return members.get(memberId); }

    public Team findTeamById(int teamId) {
        for (Team team : teams) {
            if (team.getTeamId() == teamId) return team;
        }
        return null;
    }

    public void addMemberToTeam (int teamId, int memberId) {
        Team team = findTeamById(teamId);
        if (team == null) {
            throw new IllegalArgumentException("Team with id " + teamId + " not found");
        }

        Member member = findMemberById(memberId);
        if (member == null) {
            throw new IllegalArgumentException("Member with id " + memberId + " not found");
        }

        team.addMember(member);//вже є перевірка на дубль у Team.addMember()
        System.out.println("Member added to the team successfully");
    }

    public void removeMemberFromTeam (int teamId, int memberId) {
        Team team = findTeamById(teamId);
        if (team == null) {
            throw new IllegalArgumentException("Team with id " + teamId + " not found");
        }
        Member member = findMemberById(memberId);
        if (member == null) {
            throw new IllegalArgumentException("Member with id " + memberId + " not found");
        }
        team.removeMember(memberId);
        System.out.println("Member removed from the team successfully");
    }



    public void printAllMembers() {
        if (members.isEmpty()) {
            System.out.println("No members available");
            return;
        }

        MenuPrinter.printTitle("=== MEMBERS ===");
        System.out.printf("%-5s | %-20s | %-25s | %-12s | %-12s | %-12s%n",
                "ID", "Name", "Email", "Phone", "Birthday", "Joindate");
        MenuPrinter.printSeparator();
        for (Member member : members.values()) {
            System.out.printf("%-5d | %-20s | %-25s | %-12s | %-12s | %-12s%n",
                    member.getPersonId(),
                    member.getName(),
                    member.getEmail(),
                    String.valueOf(member.getPhone()),
                    String.valueOf(member.getBirthdate()),
                    String.valueOf(member.getJoinDate()));
        }
        MenuPrinter.printSeparator();
    }

    public void printAllCoaches() {
        if (coaches.isEmpty()) {
            System.out.println("No coaches available");
            return;
        }

        MenuPrinter.printTitle("=== COACHES ===");
        System.out.printf("%-5s | %-20s | %-25s | %-12s | %-12s | %-12s%n",
                "ID", "Name", "Email", "Phone", "Birthday", "License");
        MenuPrinter.printSeparator();
        for (Coach coach : coaches.values()) {
            System.out.printf("%-5d | %-20s | %-25s | %-12s | %-12s | %-12s%n",
                    coach.getPersonId(),
                    coach.getName(),
                    coach.getEmail(),
                    String.valueOf(coach.getPhone()),
                    String.valueOf(coach.getBirthdate()),
                    String.valueOf(coach.getLicenseLevel()));
        }
        MenuPrinter.printSeparator();
    }

    public void printAllTeams() {
        if (teams.isEmpty()) {
            System.out.println("No teams available");
            return;
        }
        MenuPrinter.printTitle("=== TEAMS ===");
        System.out.printf("%-5s | %-20s | %-10s | %-20s | %-7s%n",
                "ID", "Team name", "Category", "Coach", "Members");
        MenuPrinter.printSeparator();
        for (Team team : teams) {
            System.out.printf("%-5d | %-20s | %-10s | %-20s | %-7d%n",
                    team.getTeamId(),
                    team.getNameTeam(),
                    team.getCategory(),
                    team.getCoach().getName(),
                    team.getMembers().size());
        }
        MenuPrinter.printSeparator();
    }

    public void printTeamDetails(int teamId) {
        Team team = findTeamById(teamId);
        if (team == null) {
            System.out.println("Team with id " + teamId + " not found");
            return;
        }

        MenuPrinter.printTitle("=== TEAMS ===");

        System.out.println("Team ID   : " + team.getTeamId());
        System.out.println("Name      : " + team.getNameTeam());
        System.out.println("Category  : " + team.getCategory());
        System.out.println("Coach     : " + team.getCoach().getName());
        System.out.println();

        if (team.getMembers().isEmpty()) {
            System.out.println("No members in this team");
            return;
        }

        System.out.printf("%-5s | %-20s | %-25s%n",
                "ID", "Name", "Email");
        MenuPrinter.printSeparator();
        for (Member member : team.getMembers()) {
            System.out.printf("%-5d | %-20s | %-25s%n",
                    member.getPersonId(),
                    member.getName(),
                    member.getEmail()
            );
        }
        MenuPrinter.printSeparator();

    }


}

