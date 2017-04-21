package cvut.fit.di.exception.service;

/**
 * @author Samuel Butta
 */
public class ServiceMustImplementInterfaceException extends RuntimeException {


    public ServiceMustImplementInterfaceException() {
        super("Service must implement interface exception");
    }
}
