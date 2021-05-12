package nl.traineeship.SportApp.controller;

import nl.traineeship.SportApp.domein.Trainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrainerService {
    @Autowired
    TrainerRepository trainerRepo;

    public Iterable<Trainer> alleTrainers() {
        return trainerRepo.findAll();
    }
}
