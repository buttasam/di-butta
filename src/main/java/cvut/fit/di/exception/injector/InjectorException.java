package cvut.fit.di.exception.injector;

/**
 * Nejobecnejsi runtimova vyjimka
 * pri pokusu o injektaz.
 *
 * @author Samuel Butta
 */
public class InjectorException extends RuntimeException {

    public InjectorException(Exception e) {
        e.printStackTrace();
    }

}
