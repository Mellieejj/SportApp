package nl.traineeship.SportApp.endpoints;

import nl.traineeship.SportApp.controller.SpelerService;
import nl.traineeship.SportApp.domein.Speler;
import nl.traineeship.SportApp.exceptions.SpelerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLOutput;
import java.util.Optional;

@RestController
@RequestMapping("/spelers")
public class SpelerEndPoints {
    @Autowired
    SpelerService sps;

    @CrossOrigin
    @GetMapping
    public Iterable<Speler> alleSpelers() {
        return sps.alleSpelers();
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
        System.out.println("zoek speler");
        return sps.vindSpeler(id);
    }
//    @CrossOrigin
//    @GetMapping("/zoekspeler/{naam}")
//    public Speler vindSpeler(@PathVariable String naam) {
//        System.out.println("Zoek speler met naam: " + naam);
//        return sps.zoekSpeler(naam);
//    }
    @CrossOrigin
    @GetMapping("/zoekspeler")
    public Speler vindSpeler(@RequestParam(name="speler") String naam) {
        System.out.println("Zoek speler met naam: " + naam);
        return sps.zoekSpeler(naam);
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    public void deleteSpeler(@PathVariable long id){
        System.out.println("endpoint delete");
        sps.deleteSpeler(id);
    }
}
