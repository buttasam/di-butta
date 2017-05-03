package cvut.fit.di.exception;

/**
 * @author Samuel Butta
 */
public class ServiceAlreadyExistsException extends RuntimeException {


    public ServiceAlreadyExistsException() {
        super("Service is already in service store");
    }
}
