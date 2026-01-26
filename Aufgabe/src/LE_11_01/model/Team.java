package LE_09_02.model;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private int id;
    private String name;
    private String category;

    private List<Member> members = new ArrayList<>();
    //List<Member> → “тут буде багато Member”
    //new ArrayList<>() → “створи порожній список ЗАРАЗ”
    private Coach coach;


    public Team(int id, String name, String category, Coach coach) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.coach = coach;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public List<Member> getMembers() {
        return members;
    }

    public Coach getCoach() {
        return coach;
    }


    public void addMember(Member member) {
        members.add(member);
    }

}
