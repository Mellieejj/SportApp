package nl.traineeship.SportApp.domein;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String teamNaam;
    @Enumerated
    private Speeldag speeldag;
    @ManyToMany
    private List<Trainer> trainers = new ArrayList<>();
    @OneToMany
    private List<Speler> spelers = new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTeamNaam() {
        return teamNaam;
    }

    public void setTeamNaam(String teamNaam) {
        this.teamNaam = teamNaam;
    }

    public Speeldag getSpeeldag() {
        return speeldag;
    }

    public void setSpeeldag(Speeldag speeldag) {
        this.speeldag = speeldag;
    }

    public List<Trainer> getTrainers() {
        return trainers;
    }

    public void setTrainers(Trainer trainer) {
        this.trainers.add(trainer);
    }

    public List<Speler> getSpelers() {
        return spelers;
    }

    public void setSpelers(Speler speler) {
        this.spelers.add(speler);
    }
}
