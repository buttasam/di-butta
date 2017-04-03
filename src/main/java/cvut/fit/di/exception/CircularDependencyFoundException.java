package cvut.fit.di.exception;

/**
 *
 * Vyjimka se vyhodi ve chvili, kdy ma sluzba vice konstruktoru s anotaci @Inject.
 *
 * @author Samuel Butta
 */
public class CircularDependencyFoundException extends RuntimeException {


    public CircularDependencyFoundException() {
        super("Circular dependency found, use setter or field injection or use CycleConstructorInjector implementation of injector.");
    }
}
