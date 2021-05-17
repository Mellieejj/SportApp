package nl.traineeship.SportApp.controller;

import nl.traineeship.SportApp.domein.Speler;
import nl.traineeship.SportApp.domein.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            teamRepo.save(team);
        }
    }

    public Team vindTeam(String teamNaam) {
        System.out.println("vind team: " + teamNaam);
        return teamRepo.findByTeamNaam(teamNaam).get();
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
}
