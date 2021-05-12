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

}
