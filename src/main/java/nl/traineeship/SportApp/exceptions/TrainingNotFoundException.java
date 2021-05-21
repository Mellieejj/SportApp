package nl.traineeship.SportApp.exceptions;

public class TrainingNotFoundException extends RuntimeException{
    public TrainingNotFoundException(String msg){
        super(msg);
    }
}
