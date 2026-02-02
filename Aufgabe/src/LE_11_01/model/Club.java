package LE_11_01.model;

import java.util.ArrayList;
import java.util.List;

public class Club {
    private final int clubId;
    private final String nameClub;
    private final List <Team> teams = new ArrayList<>();

    public Club(int clubId, String nameClub) {
        if (clubId <= 0) {
            throw new IllegalArgumentException("clubId must be greater than 0");
        }
        if (nameClub == null || nameClub.isBlank()) {
            throw new IllegalArgumentException("name club must not be null or blank");
        }
        this.clubId = clubId;
        this.nameClub = nameClub.trim();
    }

    public int getClubId() {return clubId;}
    public String getNameClub() {return nameClub;}
    public List <Team> getTeams() {return List.copyOf(teams);}




    public void addTeam(Team team) {
        if (team ==null) {
            throw new IllegalArgumentException("team must not be null");
        }
        teams.add(team);
    }
}
