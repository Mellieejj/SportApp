package nl.traineeship.SportApp.controller;

import nl.traineeship.SportApp.domein.Team;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
public interface TeamRepository extends CrudRepository<Team, Long> {
    Optional<Team> findByTeamNaam(String teamNaam);
}
