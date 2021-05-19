package nl.traineeship.SportApp.controller;

import nl.traineeship.SportApp.domein.Team;
import nl.traineeship.SportApp.domein.Trainer;
import nl.traineeship.SportApp.exceptions.TeamNotFoundException;
import nl.traineeship.SportApp.exceptions.TrainerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TrainerService {
    @Autowired
    TrainerRepository trainerRepo;

    @Autowired
    TeamRepository teamRepo;

    public Team vindTeam(String teamNaam){
        Optional<Team> team = teamRepo.findByTeamNaam(teamNaam);

        if(team.isEmpty()){
            throw new TeamNotFoundException("Team " + teamNaam + " niet gevonden.");
        } else {
            return team.get();
        }
    }
    public Iterable<Trainer> alleTrainers() {
        return trainerRepo.findAll();
    }

    public void addTrainer(Trainer trainer){
        trainerRepo.save(trainer);
    }

    public Trainer vindTrainer(long id){
        Optional<Trainer> trainer = trainerRepo.findById(id);
        if(trainer.isEmpty()){
            throw new TrainerNotFoundException("Trainer met id: " + id + " niet gevonden!");
        } else {
            return trainer.get();
        }
    }

    public Trainer zoekTrainerByName(String naam){
        Optional<Trainer> trainer = trainerRepo.findByNaam(naam);
        if (trainer.isEmpty()){
            throw new TrainerNotFoundException("Trainer " + naam + " niet gevonden.");
        } else {
            return trainer.get();
        }
    }

    public void deleteTrainer(long id){
        Trainer trainer = vindTrainer(id);
        trainerRepo.delete(trainer);
    }

    public void updateTrainer(long id, Trainer tr){
        Trainer trainer = vindTrainer(id);
        if (tr.getNaam() != null && !tr.getNaam().equals("")) {
            trainer.setNaam(tr.getNaam());
        }
        trainerRepo.save(trainer);
    }

    public void addTeamToTrainer(long trainerId, String teamNaam){
        Trainer trainer = vindTrainer(trainerId);
        Team team = vindTeam(teamNaam);

        if (!trainer.getTeams().contains(team)){
            trainer.addTeam(team);
            trainerRepo.save(trainer);
        }
    }

    public void deleteTeamFromTrainer(long trainerId, String teamNaam){
        Trainer trainer = vindTrainer(trainerId);
        Team team = vindTeam(teamNaam);

        trainer.removeTeam(team);
        trainerRepo.save(trainer);
    }
}
