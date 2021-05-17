package nl.traineeship.SportApp.domein;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "speler")
public class Speler {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String naam;
    private String positie;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "team_id")
    private Team team;

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

    public String getPositie() {
        return positie;
    }

    public void setPositie(String positie) {
        this.positie = positie;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
