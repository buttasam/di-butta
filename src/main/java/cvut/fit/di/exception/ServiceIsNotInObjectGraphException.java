package cvut.fit.di.exception;

/**
 * @author Samuel Butta
 */
public class ServiceIsNotInObjectGraphException extends Exception {


    public ServiceIsNotInObjectGraphException() {
        super("Service is already in service store");
    }
}
