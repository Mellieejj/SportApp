package nl.traineeship.SportApp.exceptions;

public class SpelerNotFoundException extends RuntimeException{
    public SpelerNotFoundException(long id){
        super("Speler niet gevonden " + id );

    }
}
