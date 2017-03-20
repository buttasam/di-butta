package cvut.fit.di.exception;

/**
 * @author Samuel Butta
 */
public class ServiceAlreadyExistsException extends Exception {


    public ServiceAlreadyExistsException() {
        super("Service is already in service store");
    }
}
