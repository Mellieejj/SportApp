package nl.traineeship.SportApp.exceptions;

public class TrainerNotFoundException extends RuntimeException{
    public TrainerNotFoundException(String msg){
        super(msg);
    }
}
