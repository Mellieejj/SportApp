package nl.traineeship.SportApp.endpoints;

import nl.traineeship.SportApp.controller.TrainingService;
import nl.traineeship.SportApp.domein.Training;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trainingen")
public class TrainingEndpoints {
    @Autowired
    TrainingService trainingService;

    @CrossOrigin
    @GetMapping
    public Iterable<Training> alleTrainingen() {
        return trainingService.alleTrainingen();
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public Training vindTraining(@PathVariable long id) {
        return trainingService.vindTraining(id);
    }

    @CrossOrigin
    @PostMapping
    public void nieuweTraining(@RequestBody Training training){
        trainingService.addTraining(training);
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    public void deleteTraining(@PathVariable long id){
        trainingService.deleteTraining(id);
    }

    @CrossOrigin
    @PutMapping("/{id}")
    public void updateTraining(@PathVariable long id, @RequestBody Training tr){
        trainingService.updateTraining(id, tr);
    }

    @CrossOrigin
    @PutMapping("/{trainingId}/{teamNaam}")
    public void addTeamToTraining(@PathVariable long trainingId, @PathVariable String teamNaam){
        trainingService.addTeamToTraining(trainingId, teamNaam);
    }

}
