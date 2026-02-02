package LE_11_01.model;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class Team {
    private final int teamId;
    private final String nameTeam;
    private final TeamCategory teamCategory;

    private final List<Member> members = new ArrayList<>();
    //List<Member> → “тут буде багато Member”
    //new ArrayList<>() → “створи порожній список ЗАРАЗ”
    private final Coach coach;


    public Team(int teamId, String nameTeam, Coach coach, TeamCategory teamCategory) {
        if (teamId<=0) {
            throw new IllegalArgumentException("Team ID must be a positive integer");
        }
        if (nameTeam==null|| nameTeam.isBlank()) {
            throw new IllegalArgumentException("Team name cannot be null or blank");
        }
        this.teamId = teamId;
        this.nameTeam = nameTeam;
        this.teamCategory = Objects.requireNonNull(teamCategory, "teamCategory must not be null");
        this.coach = Objects.requireNonNull(coach, "coach must not be null");

    }

    public int getTeamId() {return teamId;  }
    public String getNameTeam() {  return nameTeam;  }
    public TeamCategory getCategory() {  return teamCategory;   }
    public List<Member> getMembers() {  return List.copyOf(members);  }
    public Coach getCoach() {return coach;}

    public void addMember(Member member) {
        Objects.requireNonNull(member, "member must not be null");
        for (Member m : members) {
            if (m.getPersonId() == member.getPersonId()) {
                throw new IllegalArgumentException("Member already in team");
            }
        }
        members.add(member);
    }


    //use Iterator because removing elements in a for-each loop causes concurrent modification problems.
    public void removeMember (int memberId) {
       Iterator<Member> iterator = members.iterator(); //Дай мені спеціальний об’єкт, який дозволяє безпечно йти по members
       while (iterator.hasNext()) { //Поки в списку ще є елементи попереду
           Member member = iterator.next();//Перейди на наступний елемент і віддай мені його” (це НЕ просто читання — це рух курсора)
           if (member.getPersonId() == memberId) { //Це той member, якого я шукаю?”
               iterator.remove();//Видали той елемент, на якому Я ЗАРАЗ СТОЮ
               return;//Готово, виходимо з методу
           }
       }
       throw new IllegalArgumentException("Member not found in team");//Ми дійшли до кінця списку — такого member немає
    }

}
