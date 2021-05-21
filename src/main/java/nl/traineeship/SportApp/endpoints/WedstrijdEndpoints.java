package nl.traineeship.SportApp.endpoints;

import nl.traineeship.SportApp.controller.WedstrijdService;
import nl.traineeship.SportApp.domein.Wedstrijd;
import nl.traineeship.SportApp.exceptions.TeamNotFoundException;
import nl.traineeship.SportApp.exceptions.WedstrijdNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/wedstrijden")
public class WedstrijdEndpoints {
    @Autowired
    WedstrijdService wedstrijdService;

    @CrossOrigin
    @GetMapping
    public ResponseEntity<Iterable<Wedstrijd>> alleWedstrijden() {
        return ResponseEntity.status(HttpStatus.OK).body(wedstrijdService.alleWedstrijden());
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public Wedstrijd vindWedstrijd(@PathVariable long id) {
        try {
            return wedstrijdService.vindWedstrijd(id);
        } catch (WedstrijdNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @CrossOrigin
    @PostMapping
    public ResponseEntity nieuweWedstrijd(@RequestBody Wedstrijd wd) {
        wedstrijdService.addWedstrijd(wd);
        return ResponseEntity.status(HttpStatus.CREATED).body("Wedstrijd aangemaakt");
    }

    @CrossOrigin
    @PutMapping("/{wedstrijdId}")
    public void updateWedstrijd(@PathVariable long wedstrijdId, @RequestBody Wedstrijd wd) {
        try {
            wedstrijdService.updateWedstrijd(wedstrijdId, wd);
        } catch (WedstrijdNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @CrossOrigin
    @PutMapping("/{id}/teams")
    public void addTeamsToWedstrijd(@PathVariable long id, @RequestParam(name = "thuisteam") String teamNaam1, @RequestParam(name = "uitteam") String teamNaam2) {
        try {
            wedstrijdService.addTeams(id, teamNaam1, teamNaam2);
        } catch (TeamNotFoundException | WedstrijdNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @CrossOrigin
    @GetMapping("/perteam/{teamNaam}")
    public Iterable<Wedstrijd> wedstrijdenPerTeam(@PathVariable String teamNaam){
       return wedstrijdService.vindAlleTeamwedstrijden(teamNaam);

    }
}
