package nl.traineeship.SportApp.domein;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Training {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(cascade = CascadeType.ALL)
    private Team team;
    private String datum;

    @ManyToMany
    private List<Trainer> trainers = new ArrayList();
    @ManyToMany
    private List<Speler> opkomst = new ArrayList();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public List<Trainer> getTrainers() {
        return trainers;
    }

    public void addTrainerToTraining(Trainer trainer) {
        this.trainers.add(trainer);
    }

    public void removeTrainerFromTraining(Trainer trainer){
        this.trainers.remove(trainer);
    }

    public List<Speler> getOpkomst() {
        return opkomst;
    }

    public void addSpelerToTraining(Speler speler) {
        this.opkomst.add(speler);
    }
    public void removeSpelerFromTraining(Speler speler){
        this.opkomst.remove(speler);
    }
}
