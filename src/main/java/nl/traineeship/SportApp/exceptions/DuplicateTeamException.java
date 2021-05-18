package nl.traineeship.SportApp.exceptions;

public class DuplicateTeamException extends RuntimeException{
    public DuplicateTeamException(String msg){
        super(msg);
    }
}
