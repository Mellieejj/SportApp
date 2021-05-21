package nl.traineeship.SportApp.controller;

import nl.traineeship.SportApp.domein.Speler;
import nl.traineeship.SportApp.domein.Team;
import nl.traineeship.SportApp.domein.Trainer;
import nl.traineeship.SportApp.domein.Training;
import nl.traineeship.SportApp.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TrainingService {
    @Autowired
    TrainingRepository trainingRepo;

    @Autowired
    TeamRepository teamRepo;

    @Autowired
    SpelerRepository spelerRepo;

    @Autowired
    TrainerRepository trainerRepo;

    private Team vindTeam(String teamNaam) {
        Optional<Team> t = teamRepo.findByTeamNaam(teamNaam);
        if (t.isEmpty()) {
            throw new TeamNotFoundException("Team " + teamNaam + " niet gevonden.");
        } else {
            return t.get();
        }
    }

    private Speler vindSpeler(long id) {
        Optional<Speler> s = spelerRepo.findById(id);
        if (s.isEmpty()) {
            throw new SpelerNotFoundException("Speler met id: " + id + " niet gevonden.");
        } else {
            return s.get();
        }
    }

    private Trainer vindTrainer(long id) {
        Optional<Trainer> t = trainerRepo.findById(id);
        if (t.isEmpty()) {
            throw new TrainerNotFoundException("Trainer met id: " + id + " niet gevonden.");
        } else {
            return t.get();
        }
    }

    public Iterable<Training> alleTrainingen() {
        return trainingRepo.findAll();
    }

    public Training vindTraining(long id) {
        Optional<Training> t = trainingRepo.findById(id);
        if (t.isEmpty()) {
            throw new TrainingNotFoundException("Training met id: " + id + " niet gevonden.");
        } else {
            return t.get();
        }
    }

    public void addTraining(Training training) {
        if (training.getDatum() != null) {
            trainingRepo.save(training);
        } else {
            throw new NoDateException("De datum is verplicht.");
        }
    }

    public void deleteTraining(long id) {
        trainingRepo.delete(vindTraining(id));
    }

    public void updateTraining(long id, Training tr) {
        Training training = vindTraining(id);
        if (tr.getDatum() != null && !tr.getDatum().equals("")) {
            training.setDatum(tr.getDatum());
        }
        trainingRepo.save(training);
    }

    public void addTeamToTraining(long trainingId, String teamNaam) {
        Team team = vindTeam(teamNaam);
        Training training = vindTraining(trainingId);

        training.setTeam(team);
        trainingRepo.save(training);
    }

    public void addSpelerToTraining(long trainingId, long spelerId) {
        Training training = vindTraining(trainingId);
        Speler speler = vindSpeler(spelerId);

        training.addSpelerToTraining(speler);
        trainingRepo.save(training);
    }

    public void addTrainerToTraining(long trainingsId, long trainerId) {
        Training training = vindTraining(trainingsId);
        Trainer trainer = vindTrainer(trainerId);

        training.addTrainerToTraining(trainer);
        trainingRepo.save(training);
    }
}
