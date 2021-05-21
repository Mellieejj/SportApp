package nl.traineeship.SportApp.controller;

import nl.traineeship.SportApp.domein.Team;
import nl.traineeship.SportApp.domein.Wedstrijd;
import nl.traineeship.SportApp.exceptions.TeamNotFoundException;
import nl.traineeship.SportApp.exceptions.WedstrijdNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WedstrijdService {
    @Autowired
    WedstrijdRepository wedstrijdRepo;

    @Autowired
    TeamRepository teamRepo;

    private Team vindTeam(String teamNaam) {
        Optional<Team> team = teamRepo.findByTeamNaam(teamNaam);

        if (team.isEmpty()) {
            throw new TeamNotFoundException("Team " + teamNaam + " niet gevonden.");
        } else {
            return team.get();
        }
    }

    public Iterable<Wedstrijd> alleWedstrijden() {
        return wedstrijdRepo.findAll();
    }

    public Wedstrijd vindWedstrijd(long wedstrijdId) {
        Optional<Wedstrijd> wd = wedstrijdRepo.findById(wedstrijdId);

        if (wd.isEmpty()) {
            throw new WedstrijdNotFoundException("Wedstrijd id " + wedstrijdId + " niet gevonden");
        } else {
            return wd.get();
        }
    }

    public void addWedstrijd(Wedstrijd wd) {
        wedstrijdRepo.save(wd);
    }

    public void updateWedstrijd(long id, Wedstrijd wd) {
        Wedstrijd wedstrijd = vindWedstrijd(id);

        if (wd.getDatum() != null && !wd.getDatum().equals("")) {
            wedstrijd.setDatum(wd.getDatum());
        }
        wedstrijdRepo.save(wedstrijd);
    }

    public void addTeams(long wedstrijdId, String team1, String team2) {
        Team thuis = vindTeam(team1);
        Team uit = vindTeam(team2);

        Wedstrijd wedstrijd = vindWedstrijd(wedstrijdId);

        wedstrijd.setTeams(thuis, uit);
        wedstrijdRepo.save(wedstrijd);
    }

    public Iterable<Wedstrijd> vindAlleTeamwedstrijden(String teamNaam) {
//        Team team = vindTeam(teamNaam);
//        Iterable<Wedstrijd> lijst = wedstrijdRepo.findAll();
//        List<Wedstrijd> w = new ArrayList<>();
//
//        for(Wedstrijd item : lijst){
//            if(item.getTeams().contains(team)){
//                w.add(item);
//            }
//        }
//        return w;
        return wedstrijdRepo.findAllPerTeam(teamNaam);
    }
}
