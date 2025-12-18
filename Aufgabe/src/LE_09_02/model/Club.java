package LE_09_02.model;

import java.util.ArrayList;
import java.util.List;

public class Club {
    private String name;
    private List <Team> teams = new ArrayList<>();

    public Club(String name) {
        this.name = name;
    }

    public String getName() {return name;}
    public List <Team> getTeams() {return teams;}

    public void addTeam(Team team) {teams.add(team);}
}
