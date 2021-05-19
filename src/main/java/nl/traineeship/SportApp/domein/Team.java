package nl.traineeship.SportApp.domein;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import nl.traineeship.SportApp.exceptions.DuplicateSpelerException;
import nl.traineeship.SportApp.exceptions.SelectieException;
import nl.traineeship.SportApp.exceptions.SpelerNotFoundException;

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

    @OneToMany
//    @JsonManagedReference
    private List<Speler> spelers = new ArrayList<>();

    @OneToMany
    private List<Speler> selectie = new ArrayList<>();

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
        System.out.println("speler toevoegen");
        if(!this.getSpelers().contains(speler)){
            spelers.add(speler);
            speler.setTeam(this);
        } else {
            throw new DuplicateSpelerException("Speler zit al in het team.");
        }
    }

    public List<Speler> getSelectie() {
        return selectie;
    }

    private boolean isAlreadyAKeeper() {
        for (Speler s : getSelectie()) {
            if (s.getPositie().equalsIgnoreCase("Keeper")) {
                return true;
            }
        }
        return false;
    }

    public void addSpelerToSelectie(Speler speler){
        if(getSelectie().size() < 11){
            if (this.getSpelers().contains(speler)){
                if(speler.getPositie().equalsIgnoreCase("Keeper") && isAlreadyAKeeper()){
                    throw new SelectieException("Er mag maar 1 keeper in de selectie zitten");
                } else {
                    if(!this.selectie.contains(speler)){
                        this.selectie.add(speler);
                    } else {
                        throw new DuplicateSpelerException("Speler zit al in selectie");
                    }
                }
            } else {
               throw new SpelerNotFoundException("Speler niet gevonden in spelerlijst.");
            }
        } else {
            throw new SelectieException("Selectie bestaat al uit elf spelers");
        }
    }
}
