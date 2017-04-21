package cvut.fit.di.exception.service;

/**
 * @author Samuel Butta
 */
public class ServiceAndImplementationDoesNotMatchException extends RuntimeException {


    public ServiceAndImplementationDoesNotMatchException() {
        super("Service implements more interfaces exception");
    }
}
