package nl.traineeship.SportApp.controller;

import nl.traineeship.SportApp.domein.Speler;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface SpelerRepository extends CrudRepository<Speler, Long> {
    Optional<Speler> findByNaam(String naam);
}
