package nl.traineeship.SportApp.controller;

import nl.traineeship.SportApp.domein.Training;
import org.springframework.data.repository.CrudRepository;

public interface TrainingRepository extends CrudRepository<Training, Long> {

}
