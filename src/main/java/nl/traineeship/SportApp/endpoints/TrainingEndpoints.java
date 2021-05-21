package nl.traineeship.SportApp.endpoints;

import nl.traineeship.SportApp.controller.TrainingService;
import nl.traineeship.SportApp.domein.Training;
import nl.traineeship.SportApp.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/trainingen")
public class TrainingEndpoints {
    @Autowired
    TrainingService trainingService;

    @CrossOrigin
    @GetMapping
    public ResponseEntity<Iterable<Training>> alleTrainingen() {
        return ResponseEntity.status(HttpStatus.OK).body(trainingService.alleTrainingen());
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public Training vindTraining(@PathVariable long id) {
        try {
            return trainingService.vindTraining(id);
        } catch (TrainingNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @CrossOrigin
    @PostMapping
    public ResponseEntity<String> nieuweTraining(@RequestBody Training training) {
        try {
            trainingService.addTraining(training);
            return ResponseEntity.status(HttpStatus.CREATED).body("Training op " + training.getDatum() + " toegevoegd.");
        } catch (NoDateException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    public void deleteTraining(@PathVariable long id) {
        try {
            trainingService.deleteTraining(id);
        } catch (TrainerNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @CrossOrigin
    @PutMapping("/{id}")
    public void updateTraining(@PathVariable long id, @RequestBody Training tr) {
        try {
            trainingService.updateTraining(id, tr);
        } catch (TrainingNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @CrossOrigin
    @PutMapping("/{trainingId}/{teamNaam}")
    public void addTeamToTraining(@PathVariable long trainingId, @PathVariable String teamNaam) {
        try {
            trainingService.addTeamToTraining(trainingId, teamNaam);
        } catch (TrainingNotFoundException | TeamNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @CrossOrigin
    @PutMapping("/{trainingsId}/spelers/{spelerId}")
    public void addSpelerToTraining(@PathVariable long trainingsId, @PathVariable long spelerId) {
        try {
            trainingService.addSpelerToTraining(trainingsId, spelerId);
        } catch (SpelerNotFoundException | TrainingNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @CrossOrigin
    @PutMapping("/{trainingsId}/trainers/{trainerId}")
    public void addTrainerToTraining(@PathVariable long trainingsId, @PathVariable long trainerId) {
        try {
            trainingService.addTrainerToTraining(trainingsId, trainerId);
        } catch (TrainingNotFoundException | TrainerNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }
}
