package nl.traineeship.SportApp.domein;

import nl.traineeship.SportApp.exceptions.TeamNotFoundException;
import org.hibernate.annotations.IndexColumn;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Wedstrijd {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    String datum;
    @ManyToMany
    @IndexColumn(name = "wedstrijden")
    private List<Team> teams = new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(Team thuisSpelend, Team uitSpelend) {
        if (thuisSpelend == null || uitSpelend == null){
            throw new TeamNotFoundException("Team kan niet null zijn.");
        }

        if(this.teams.size() == 0){
            this.teams.add(0, thuisSpelend);
            this.teams.add(1, uitSpelend);
        } else {
            this.teams.set(0, thuisSpelend);
            this.teams.set(1, uitSpelend);
        }
    }
}
