package nl.traineeship.SportApp.exceptions;

public class DuplicateSpelerException extends RuntimeException{
    public DuplicateSpelerException(String msg){
        super(msg);
    }
}
