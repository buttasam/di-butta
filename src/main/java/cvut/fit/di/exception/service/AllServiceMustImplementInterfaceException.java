package cvut.fit.di.exception.service;

/**
 * @author Samuel Butta
 */
public class AllServiceMustImplementInterfaceException extends RuntimeException {


    public AllServiceMustImplementInterfaceException() {
        super("Using cycle constructor, all service must implement interface");
    }
}
