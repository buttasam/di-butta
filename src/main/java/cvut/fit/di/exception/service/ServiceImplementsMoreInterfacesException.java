package cvut.fit.di.exception.service;

/**
 * @author Samuel Butta
 */
public class ServiceImplementsMoreInterfacesException extends RuntimeException {


    public ServiceImplementsMoreInterfacesException() {
        super("Service implements more interfaces exception");
    }
}
