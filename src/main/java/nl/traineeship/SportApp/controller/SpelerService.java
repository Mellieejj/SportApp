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
        Optional<Speler> speler = spelerRepo.findById(spelerId);
        if (speler.isEmpty()) {
            throw new SpelerNotFoundException("Speler met id: " + spelerId + " niet gevonden.");
        } else {
            return speler.get();
        }
    }

    public Speler zoekSpeler(String naam) {
        Optional<Speler> speler = spelerRepo.findByNaam(naam);
        if (speler.isEmpty()) {
            throw new SpelerNotFoundException("Speler " + naam + " niet gevonden.");
        } else {
            return speler.get();
        }
    }

    public void deleteSpeler(long id) {
        Speler speler = vindSpeler(id);
        spelerRepo.delete(speler);
    }

    public void updateSpeler(long id, Speler speler) {
        Speler sp = vindSpeler(id);
        if (speler.getNaam() != null && !speler.getNaam().equals("")) {
            sp.setNaam(speler.getNaam());
        }
        if (speler.getPositie() != null && !speler.getPositie().equals("")) {
            sp.setPositie(speler.getPositie());
        }
        spelerRepo.save(sp);
    }
}
