package nl.traineeship.SportApp.domein;

import org.hibernate.annotations.IndexColumn;

import javax.persistence.*;

@Entity
public class Wedstrijd {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToMany
    @IndexColumn(name="wedstrijden")
    private final Team[] teams = new Team[2];

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Team[] getTeams() {
        return teams;
    }

    public void setTeams( Team thuisSpelend, Team uitSpelend) {
        this.teams[0] = thuisSpelend;
        this.teams[1] = uitSpelend;
    }
}
