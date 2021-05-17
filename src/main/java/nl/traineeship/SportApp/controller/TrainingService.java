package nl.traineeship.SportApp.controller;

import nl.traineeship.SportApp.domein.Speler;
import nl.traineeship.SportApp.domein.Team;
import nl.traineeship.SportApp.domein.Trainer;
import nl.traineeship.SportApp.domein.Training;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Iterable<Training> alleTrainingen() {
        return trainingRepo.findAll();
    }

    public Training vindTraining(long id) {
        return trainingRepo.findById(id).get();
    }

    public void addTraining(Training training){
        trainingRepo.save(training);
    }

    public void deleteTraining(long id){
        trainingRepo.delete(vindTraining(id));
    }

    public void updateTraining(long id, Training tr){
        System.out.println("update " + tr.getId());
        Training training = vindTraining(id);
        if (tr.getDatum() != null && !tr.getDatum().equals("")) {
            training.setDatum(tr.getDatum());
        }
       trainingRepo.save(training);
    }

    public void addTeamToTraining(long trainingId, String teamNaam){
        Team team = teamRepo.findByTeamNaam(teamNaam).get();
        Training training = vindTraining(trainingId);

        training.setTeam(team);
        trainingRepo.save(training);
    }

    public void addSpelerToTraining(long trainingId, long spelerId){
        Training training = vindTraining(trainingId);
        Speler speler = spelerRepo.findById(spelerId).get();

        training.addSpelerToTraining(speler);
        trainingRepo.save(training);
    }

    public void addTrainerToTraining(long trainingsId, long trainerId){
        Training training = vindTraining(trainingsId);
        Trainer trainer = trainerRepo.findById(trainerId).get();

        training.addTrainerToTraining(trainer);
        trainingRepo.save(training);
    }
}
