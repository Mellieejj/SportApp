package nl.traineeship.SportApp.domein;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "team")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

   @Column(unique = true)
    private String teamNaam;

   @Enumerated
    private Speeldag speeldag;

    @ManyToMany(mappedBy = "teams")
    @JsonBackReference
    private List<Trainer> trainers = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JsonManagedReference
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

    public void addTrainer(Trainer trainer) {
        this.trainers.add(trainer);
    }

    public List<Speler> getSpelers() {
        return spelers;
    }

    public void addSpeler(Speler speler) {
        spelers.add(speler);
        speler.setTeam(this);
    }
}
