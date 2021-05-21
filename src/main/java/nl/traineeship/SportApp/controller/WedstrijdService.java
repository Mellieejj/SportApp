package nl.traineeship.SportApp.controller;

import nl.traineeship.SportApp.domein.Team;
import nl.traineeship.SportApp.domein.Wedstrijd;
import nl.traineeship.SportApp.exceptions.NoDateException;
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
        if (wd.getDatum() != null){
            wedstrijdRepo.save(wd);
        } else {
            throw new NoDateException("Datum is verplicht.");
        }
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

    public Iterable<Wedstrijd> vindAlleTeamWedstrijden(String teamNaam) {
        vindTeam(teamNaam);
        return wedstrijdRepo.findAllPerTeam(teamNaam);
    }

    public void deleteWedstrijd(long id){
        Wedstrijd w = vindWedstrijd(id);

        wedstrijdRepo.delete(w);
    }
}
