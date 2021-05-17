package nl.traineeship.SportApp.endpoints;

import nl.traineeship.SportApp.controller.TeamService;
import nl.traineeship.SportApp.domein.Speler;
import nl.traineeship.SportApp.domein.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teams")
public class TeamEndpoint {
    @Autowired
    TeamService teamService;

    @CrossOrigin
    @GetMapping
    public Iterable<Team> alleTeams() {
        return teamService.alleTeams();
    }

    @CrossOrigin
    @PostMapping
    public void nieuwTeam(@RequestBody Team team) {
        teamService.addTeam(team);
        System.out.println(team.getTeamNaam());
    }

    @CrossOrigin
    @GetMapping("/{teamNaam}")
    public Team vindTeam(@PathVariable String teamNaam) {
        return teamService.vindTeam(teamNaam);
    }

    @CrossOrigin
    @PutMapping("/{teamNaam}")
    public void updateTeam(@PathVariable String teamNaam, @RequestBody Team team) {
        teamService.updateTeam(teamNaam, team);
    }

    @CrossOrigin
    @DeleteMapping("/{teamNaam}")
    public void deleteTeam(@PathVariable String teamNaam) {
        teamService.deleteTeam(teamNaam);
    }

    @CrossOrigin
    @PutMapping("/{teamNaam}/{spelerId}")
    public void addSpelerToTeam(@PathVariable String teamNaam, @PathVariable long spelerId) {
        teamService.addSpelerToTeam(teamNaam, spelerId);
    }
}
