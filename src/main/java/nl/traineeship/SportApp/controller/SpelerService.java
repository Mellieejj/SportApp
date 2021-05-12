package nl.traineeship.SportApp.controller;

import nl.traineeship.SportApp.domein.Speler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SpelerService {
    @Autowired
    SpelerRepository spelerRepo;

    public Iterable<Speler> alleSpelers() {
        return spelerRepo.findAll();
    }

    public void addSpeler(Speler sp) {
        spelerRepo.save(sp);
    }

    public Speler vindSpeler(long spelerId) {
        return spelerRepo.findById(spelerId).get();
    }

    public Speler zoekSpeler(String naam) {
        return spelerRepo.findByNaam(naam).get();
    }

    public void deleteSpeler(long id){
        Speler speler = spelerRepo.findById(id).get();
        System.out.println("delete: " + speler);
        spelerRepo.delete(speler);


    }
}
