package nl.traineeship.SportApp.exceptions;

public class WedstrijdNotFoundException extends RuntimeException{
    public WedstrijdNotFoundException(String msg){
        super(msg);
    }
}
