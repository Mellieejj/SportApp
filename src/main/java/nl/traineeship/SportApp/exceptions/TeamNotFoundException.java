package nl.traineeship.SportApp.exceptions;

public class TeamNotFoundException extends RuntimeException{
    public TeamNotFoundException(String msg){
        super(msg);
    }
}
