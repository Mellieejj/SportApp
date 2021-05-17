package nl.traineeship.SportApp.endpoints;

import nl.traineeship.SportApp.controller.TrainerService;
import nl.traineeship.SportApp.domein.Trainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trainers")
public class TrainerEndpoints {
    @Autowired
    TrainerService ts;

    @CrossOrigin
    @GetMapping
    public Iterable<Trainer> alleTrainers(){
        return ts.alleTrainers();
    }

    @CrossOrigin
    @PostMapping
    public void nieuweTrainer(@RequestBody Trainer trainer){
        ts.addTrainer(trainer);
        System.out.println(trainer.getNaam());
    }

    @CrossOrigin
    @GetMapping("/zoektrainer")
    public Trainer zoekTrainerOpNaam(@RequestParam(name= "trainer") String naam){
        System.out.println("Vind trainer met naam: " + naam);
        return ts.zoekTrainerByName(naam);
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public Trainer vindTrainer(@PathVariable long id){
        System.out.println("Vind Trainer met id: " + id);
        return ts.vindTrainer(id);
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    public void deleteTrainer(@PathVariable long id){
        System.out.println("Delete Trainer met id: " + id);
        ts.deleteTrainer(id);
    }

    @CrossOrigin
    @PutMapping("/{id}")
    public void updateTrainer(@PathVariable long id, @RequestBody Trainer trainer){
        ts.updateTrainer(id, trainer);
    }
}
