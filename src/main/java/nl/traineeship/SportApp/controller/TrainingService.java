package nl.traineeship.SportApp.controller;

import nl.traineeship.SportApp.domein.Speler;
import nl.traineeship.SportApp.domein.Team;
import nl.traineeship.SportApp.domein.Training;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrainingService {
    @Autowired
    TrainingRepository trainingRepo;

    @Autowired
    TeamRepository teamRepo;

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
}
