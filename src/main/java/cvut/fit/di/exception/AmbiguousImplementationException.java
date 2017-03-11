package cvut.fit.di.exception;

/**
 * @author Samuel Butta
 */
public class AmbiguousImplementationException extends Exception {


    public AmbiguousImplementationException() {
        super("Ambiguous implementation of interface");
    }
}
