package nl.traineeship.SportApp.controller;

import nl.traineeship.SportApp.domein.Wedstrijd;
import org.springframework.data.repository.CrudRepository;

public interface WedstrijdRepository extends CrudRepository<Wedstrijd, Long> {
}
