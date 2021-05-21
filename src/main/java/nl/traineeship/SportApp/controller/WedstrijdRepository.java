package nl.traineeship.SportApp.controller;

import nl.traineeship.SportApp.domein.Wedstrijd;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public interface WedstrijdRepository extends CrudRepository<Wedstrijd, Long> {
    @Query("select w from Wedstrijd w left join w.teams t where t.teamNaam = ?1")
    List<Wedstrijd> findAllPerTeam(String teamNaam);
}
