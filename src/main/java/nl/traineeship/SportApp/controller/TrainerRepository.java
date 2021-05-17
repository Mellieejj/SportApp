package nl.traineeship.SportApp.controller;

import nl.traineeship.SportApp.domein.Trainer;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TrainerRepository extends CrudRepository<Trainer, Long> {
    Optional<Trainer> findByNaam(String naam);
}
