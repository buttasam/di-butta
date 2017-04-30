package cvut.fit.di.proxy;


/**
 * Pomocne rozhrani s metodou,
 * ktera umozni nastaveni instance pro proxy tridu.
 *
 * @param <T> typ rozhrani skutecneho objektu
 * @author Samuel Butta
 */
public interface InstanceSetter<T> {

    void setInstance(T instance);

}
