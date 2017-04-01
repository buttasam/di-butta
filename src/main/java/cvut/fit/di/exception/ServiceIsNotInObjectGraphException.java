package cvut.fit.di.exception;

/**
 * @author Samuel Butta
 */
public class ServiceIsNotInObjectGraphException extends RuntimeException {


    public ServiceIsNotInObjectGraphException() {
        super("Service is already in service store");
    }
}
