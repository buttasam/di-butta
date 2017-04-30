package cvut.fit.di.proxy;


import cvut.fit.di.anotation.proxy.TargetInstanceSetter;

/**
 * Pomocne rozhrani s metodou,
 * ktera umozni nastaveni instance pro proxy tridu.
 *
 * @param <T> typ rozhrani skutecneho objektu
 * @author Samuel Butta
 */
public interface InstanceSetter<T> {

    @TargetInstanceSetter
    void setInstance(T instance);

}
