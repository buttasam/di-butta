package cvut.fit.di.exception;

/**
 * @author Samuel Butta
 */
public class MissingImplementationException extends RuntimeException {


    public MissingImplementationException() {
        super("Missing implementation of interface");
    }
}
