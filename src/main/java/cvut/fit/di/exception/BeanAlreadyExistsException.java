package cvut.fit.di.exception;

/**
 * @author Samuel Butta
 */
public class BeanAlreadyExistsException extends Exception {


    public BeanAlreadyExistsException() {
        super("Bean is already in bean store");
    }
}
