package nl.traineeship.SportApp.controller;

import nl.traineeship.SportApp.domein.Speler;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface SpelerRepository extends CrudRepository<Speler, Long> {
    Optional<Speler> findByNaam(String naam);

    @Query("select s from Speler s where lower(s.positie) = lower(?1)")
    List<Speler> findByPositieC(String positie);

}
