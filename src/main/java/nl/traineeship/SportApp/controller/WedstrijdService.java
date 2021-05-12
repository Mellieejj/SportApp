package nl.traineeship.SportApp.controller;

import nl.traineeship.SportApp.domein.Wedstrijd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WedstrijdService {
    @Autowired
    WedstrijdRepository wedstrijdRepo;

    public Iterable<Wedstrijd> alleWedstrijden(){
        return wedstrijdRepo.findAll();
    }
}
