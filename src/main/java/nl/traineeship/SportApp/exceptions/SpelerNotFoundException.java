package nl.traineeship.SportApp.exceptions;

public class SpelerNotFoundException extends RuntimeException{
    public SpelerNotFoundException(String message){
        super(message);
    }
}
