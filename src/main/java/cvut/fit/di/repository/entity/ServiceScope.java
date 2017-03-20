package cvut.fit.di.repository.entity;

/**
 * Scope urcuje dobu zivotnosti Service.
 * Tento enum urcuje typy, ktere DI framework podporuje.
 *
 * @author Samuel Butta
 */
public enum ServiceScope {

    /**
     * Jedina instance napric celou aplikaci
     */
    SINGLETON,
    /**
     * Vytvari pokazde nova instance.
     */
    PROTOTYPE

}
