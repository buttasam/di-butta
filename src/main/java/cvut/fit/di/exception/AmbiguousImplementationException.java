package cvut.fit.di.exception;

/**
 * @author Samuel Butta
 */
public class AmbiguousImplementationException extends RuntimeException {


    public AmbiguousImplementationException() {
        super("Ambiguous implementation of interface");
    }
}
