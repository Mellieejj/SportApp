package nl.traineeship.SportApp.controller;

import nl.traineeship.SportApp.domein.Training;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrainingService {
    @Autowired
    TrainingRepository trainingRepo;

    public Iterable<Training> alleTrainingen() {
        return trainingRepo.findAll();
    }

    public Training vindTraining(long id) {
        return trainingRepo.findById(id).get();
    }

    public void addTraining(Training training){
        trainingRepo.save(training);
    }
}
