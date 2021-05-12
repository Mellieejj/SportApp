package nl.traineeship.SportApp.controller;

import nl.traineeship.SportApp.domein.Speler;
import nl.traineeship.SportApp.exceptions.SpelerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
        return spelerRepo.findByNaam(naam);
    }
}
