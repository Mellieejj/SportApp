package nl.traineeship.SportApp.endpoints;

import nl.traineeship.SportApp.controller.TeamService;
import nl.traineeship.SportApp.domein.Speler;
import nl.traineeship.SportApp.domein.Team;
import nl.traineeship.SportApp.domein.Trainer;
import nl.traineeship.SportApp.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/teams")
public class TeamEndpoint {
    @Autowired
    TeamService teamService;

    @CrossOrigin
    @GetMapping
    public ResponseEntity<Iterable<Team>> allTeams() {
        return ResponseEntity.status(HttpStatus.OK).body(teamService.alleTeams());
    }

    @CrossOrigin
    @PostMapping
    public ResponseEntity<String> nieuwTeam(@RequestBody Team team) {
        try {
            teamService.addTeam(team);
            return ResponseEntity.status(HttpStatus.CREATED).body("Team aangemaakt: " + team.getTeamNaam());
        } catch (DuplicateTeamException e) {
            throw new ResponseStatusException(HttpStatus.valueOf(400), e.getMessage(), e);
        }
    }

    @CrossOrigin
    @GetMapping("/{teamNaam}")
    public ResponseEntity<Team> vindTeam(@PathVariable String teamNaam) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(teamService.vindTeam(teamNaam));
        } catch (TeamNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @CrossOrigin
    @PutMapping("/{teamNaam}")
    public void updateTeam(@PathVariable String teamNaam, @RequestBody Team team) {
        try {
            teamService.updateTeam(teamNaam, team);
        } catch (TeamNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @CrossOrigin
    @DeleteMapping("/{teamNaam}")
    public void deleteTeam(@PathVariable String teamNaam) {
        try {
            teamService.deleteTeam(teamNaam);
        } catch (TeamNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @CrossOrigin
    @PutMapping("/{teamNaam}/{spelerId}")
    public void addSpelerToTeam(@PathVariable String teamNaam, @PathVariable long spelerId) {
        try {
            teamService.addSpelerToTeam(teamNaam, spelerId);
        } catch (TeamNotFoundException | SpelerNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @CrossOrigin
    @DeleteMapping("/{teamNaam}/{spelerId}")
    public void deleteSpeler(@PathVariable String teamNaam, @PathVariable long spelerId){
        try {
            teamService.deleteSpeler(teamNaam, spelerId);
        } catch (TeamNotFoundException | SpelerNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @CrossOrigin
    @GetMapping("/{teamNaam}/trainers")
    public List<Trainer> teamTrainers(@PathVariable String teamNaam) {
        try {
            return teamService.vindTrainers(teamNaam);
        } catch (TeamNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @CrossOrigin
    @GetMapping("/{teamNaam}/selectie")
    public ResponseEntity<List<Speler>> getSelectie(@PathVariable String teamNaam) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(teamService.vindSelectie(teamNaam));
        } catch (TeamNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @CrossOrigin
    @PutMapping("/{teamNaam}/selectie/{spelerId}")
    public void addToSelectie(@PathVariable String teamNaam, @PathVariable long spelerId) {
        try {
            teamService.addToSelectie(teamNaam, spelerId);
        } catch (SpelerNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (DuplicateSpelerException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        } catch (SelectieException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }
}
