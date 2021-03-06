package nl.traineeship.SportApp.endpoints;

import nl.traineeship.SportApp.controller.TrainerService;
import nl.traineeship.SportApp.domein.Trainer;
import nl.traineeship.SportApp.exceptions.TeamNotFoundException;
import nl.traineeship.SportApp.exceptions.TrainerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/trainers")
public class TrainerEndpoints {
    @Autowired
    TrainerService ts;

    @CrossOrigin
    @GetMapping
    public ResponseEntity<Iterable<Trainer>> alleTrainers() {
        return ResponseEntity.status(HttpStatus.OK).body(ts.alleTrainers());
    }

    @CrossOrigin
    @PostMapping
    public ResponseEntity<String> nieuweTrainer(@RequestBody Trainer trainer) {
        ts.addTrainer(trainer);
        return ResponseEntity.status(HttpStatus.OK).body(trainer.getNaam() + " toegevoegd.");
    }

    @CrossOrigin
    @GetMapping("/zoektrainer")
    public Trainer zoekTrainerOpNaam(@RequestParam(name = "trainer") String naam) {
        try {
            return ts.zoekTrainerByName(naam);
        } catch (TrainerNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getLocalizedMessage(), e);
        }
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public Trainer vindTrainer(@PathVariable long id) {
        try {
            return ts.vindTrainer(id);
        } catch (TrainerNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    public void deleteTrainer(@PathVariable long id) {
        try {
            System.out.println("Delete Trainer met id: " + id);
            ts.deleteTrainer(id);
        } catch (TrainerNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @CrossOrigin
    @PutMapping("/{id}")
    public void updateTrainer(@PathVariable long id, @RequestBody Trainer trainer) {
        try {
            ts.updateTrainer(id, trainer);
        } catch (TrainerNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @CrossOrigin
    @PutMapping("/{trainerId}/teams/{teamNaam}")
    public void addTeamToTrainer(@PathVariable long trainerId, @PathVariable String teamNaam) {
        try {
            ts.addTeamToTrainer(trainerId, teamNaam);
        } catch (TrainerNotFoundException | TeamNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @CrossOrigin
    @DeleteMapping("{trainerId}/teams/{teamNaam}")
    public void deleteTeamFromTrainer(@PathVariable long trainerId, @PathVariable String teamNaam) {
        try {
            ts.deleteTeamFromTrainer(trainerId, teamNaam);
        } catch (TrainerNotFoundException | TeamNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }
}
