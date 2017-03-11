package cvut.fit.di.exception;

/**
 * @author Samuel Butta
 */
public class MissingImplementationException extends Exception {


    public MissingImplementationException() {
        super("Missing implementation of interface");
    }
}
