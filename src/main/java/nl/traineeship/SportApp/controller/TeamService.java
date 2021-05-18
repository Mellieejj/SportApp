package nl.traineeship.SportApp.controller;

import nl.traineeship.SportApp.domein.Speler;
import nl.traineeship.SportApp.domein.Team;
import nl.traineeship.SportApp.domein.Trainer;
import nl.traineeship.SportApp.exceptions.DuplicateTeamException;
import nl.traineeship.SportApp.exceptions.TeamNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamService {
    @Autowired
    TeamRepository teamRepo;

    @Autowired
    SpelerRepository spelerRepo;

    public Iterable<Team> alleTeams() {
        return teamRepo.findAll();
    }

    public void addTeam(Team team) {
        if (team.getTeamNaam() != null) {
            alleTeams().forEach(t -> {
                if (t.getTeamNaam().equals(team.getTeamNaam())){
                    throw new DuplicateTeamException("Team naam bestaat al.");
                }
            });
            teamRepo.save(team);
        }
    }

    public Team vindTeam(String teamNaam) {
        System.out.println("vind team: " + teamNaam);
        Optional<Team> team = teamRepo.findByTeamNaam(teamNaam);
        if(team.isEmpty()){
            throw new TeamNotFoundException("Team niet gevonden.");
        } else {
            return team.get();
        }
    }

//    public Team vindTeam(long id) {
//        return teamRepo.findById(id).get();
//    }

    public void deleteTeam(String teamNaam) {
        Team team = vindTeam(teamNaam);
        teamRepo.delete(team);
    }

    public void updateTeam(String teamNaam, Team team) {
        System.out.println("update " + team.getTeamNaam());
        Team t = vindTeam(teamNaam);
        if (team.getTeamNaam() != null && !team.getTeamNaam().equals("")) {
            t.setTeamNaam(team.getTeamNaam());
        }
        if (team.getSpeeldag() != null) {
            t.setSpeeldag(team.getSpeeldag());
        }
        teamRepo.save(t);
    }

    public void addSpelerToTeam(String teamNaam, long spelerId) {
        Speler speler = spelerRepo.findById(spelerId).get();
        Team team = vindTeam(teamNaam);
        if (speler.getTeam() != null) {
            speler.setTeam(null);
            for (Speler sp : team.getSpelers()) {
                if (sp.equals(speler)) {
                    team.getSpelers().remove(speler);
                }
            }
        }
        team.addSpeler(speler);
        teamRepo.save(team);
    }

    public List<Trainer> vindTrainers(String teamNaam){
        Team team = vindTeam(teamNaam);
        return team.getTrainers();
    }

    public List<Speler> vindSelectie(String teamNaam){
        Team team = vindTeam(teamNaam);
        return team.getSelectie();
    }

    public void addToSelectie(String teamNaam, long spelerId){
        Team team = vindTeam(teamNaam);
        Speler speler = spelerRepo.findById(spelerId).get();
        team.addSpelerToSelectie(speler);
        teamRepo.save(team);
    }
}
