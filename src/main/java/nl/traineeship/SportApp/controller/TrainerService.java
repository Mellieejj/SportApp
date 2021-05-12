package nl.traineeship.SportApp.controller;

import nl.traineeship.SportApp.domein.Trainer;
import nl.traineeship.SportApp.exceptions.SpelerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrainerService {
    @Autowired
    TrainerRepository trainerRepo;

    public Iterable<Trainer> alleTrainers() {
        return trainerRepo.findAll();
    }

    public void addTrainer(Trainer trainer){
        trainerRepo.save(trainer);
    }

    public Trainer vindTrainer(long id){
        return trainerRepo.findById(id).orElseThrow(() -> new SpelerNotFoundException(id));
    }

    public void deleteTrainer(long id){
        Trainer trainer = vindTrainer(id);
        trainerRepo.delete(trainer);
    }

    public void updateTrainer(long id, Trainer tr){

    }
}
