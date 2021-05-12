package nl.traineeship.SportApp.controller;

import nl.traineeship.SportApp.domein.Trainer;
import org.springframework.data.repository.CrudRepository;

public interface TrainerRepository extends CrudRepository<Trainer, Long> {

}
