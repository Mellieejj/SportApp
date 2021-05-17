package nl.traineeship.SportApp.controller;

import nl.traineeship.SportApp.domein.Speler;
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
        return trainerRepo.findById(id).get();
    }

    public Trainer zoekTrainerByName(String naam){
        return trainerRepo.findByNaam(naam).get();
    }

    public void deleteTrainer(long id){
        Trainer trainer = vindTrainer(id);
        trainerRepo.delete(trainer);
    }

    public void updateTrainer(long id, Trainer tr){
        System.out.println("update " + tr.getNaam());
        Trainer trainer = vindTrainer(id);

        if (tr.getNaam() != null && !tr.getNaam().equals("")) {
            trainer.setNaam(tr.getNaam());
        }

        trainerRepo.save(trainer);
    }
}
