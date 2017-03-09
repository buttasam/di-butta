package cvut.fit.di.repository.entity;

/**
 * Scope urcuje dobu zivotnosti Beany.
 * Tento enum urcuje typy, ktere DI framework podporuje.
 *
 * @author Samuel Butta
 */
public enum BeanScope {

    /**
     * Jedina instance napric celou aplikaci
     */
    SINGLETON,
    /**
     * Vytvari pokazde nova instance.
     */
    PROTOTYPE

}
