package cvut.fit.di.exception;

/**
 *
 * Vyjimka se vyhodi ve chvili, kdy ma sluzba vice konstruktoru s anotaci @Inject.
 *
 * @author Samuel Butta
 */
public class AmbiguousConstructorException extends Exception {


    public AmbiguousConstructorException() {
        super("Ambiguous constructor of injected class");
    }
}
