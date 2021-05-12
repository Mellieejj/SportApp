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
    @ManyToOne
    private Team team;
    private LocalDate datum;

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

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public List<Trainer> getTrainers() {
        return trainers;
    }

    public void setTrainers(Trainer trainers) {
        this.trainers.add(trainers);
    }

    public List<Speler> getOpkomst() {
        return opkomst;
    }

    public void setOpkomst(Speler opkomst) {
        this.opkomst.add(opkomst);
    }
}
