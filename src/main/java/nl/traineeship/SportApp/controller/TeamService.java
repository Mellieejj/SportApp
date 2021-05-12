package nl.traineeship.SportApp.controller;

import nl.traineeship.SportApp.domein.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamService {
    @Autowired
    TeamRepository teamRepo;

    public Iterable<Team> alleTeams(){
        return teamRepo.findAll();
    }
}
