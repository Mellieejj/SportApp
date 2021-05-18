package nl.traineeship.SportApp.domein;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Trainer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String naam;

    @ManyToMany(cascade = {CascadeType.PERSIST}, fetch = FetchType.LAZY)
//    @JsonManagedReference
    @JoinTable(
            name = "trainer_teams",
            joinColumns = @JoinColumn(name = "trainer_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "team_id", referencedColumnName = "id")
    )
    private List<Team> teams = new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void addTeam(Team team) {
        if (!this.teams.contains(team)) {
            this.teams.add(team);
            team.addTrainer(this);
        }
    }

    public void removeTeam(Team team) {
        this.teams.remove(team);
    }
}

