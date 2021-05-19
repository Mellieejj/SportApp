package nl.traineeship.SportApp.endpoints;

import nl.traineeship.SportApp.controller.SpelerService;
import nl.traineeship.SportApp.domein.Speler;
import nl.traineeship.SportApp.exceptions.SpelerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("/spelers")
public class SpelerEndPoints {
    @Autowired
    SpelerService sps;

    @CrossOrigin
    @GetMapping
    public ResponseEntity<Iterable<Speler>> alleSpelers() {
        return ResponseEntity.status(HttpStatus.OK).body(sps.alleSpelers());
    }

    @CrossOrigin
    @PostMapping
    public void nieuweSpeler(@RequestBody Speler speler) {
            sps.addSpeler(speler);
            System.out.println(speler.getNaam());
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public Speler eenSpeler(@PathVariable long id) {
        try {
            System.out.println("zoek speler: " + id);
            return sps.vindSpeler(id);
        } catch(SpelerNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(),e);

        }
    }

    @CrossOrigin
    @GetMapping("/zoekspeler")
    public Speler vindSpeler(@RequestParam(name = "speler") String naam) {
        try {
            System.out.println("Zoek speler met naam: " + naam);
            return sps.zoekSpeler(naam);
        } catch (SpelerNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    public void deleteSpeler(@PathVariable long id) {
        try {
            System.out.println("Delete Speler met id: " + id);
            sps.deleteSpeler(id);
        } catch (SpelerNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @CrossOrigin
    @PutMapping("/{id}")
    public void updateSpeler(@PathVariable long id, @RequestBody Speler speler) {
        try {
            System.out.println("update speler met id: " + id);
            sps.updateSpeler(id, speler);
        } catch (SpelerNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }
}
