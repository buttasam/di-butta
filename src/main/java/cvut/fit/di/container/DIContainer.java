package cvut.fit.di.container;

import cvut.fit.di.builder.Executor;
import cvut.fit.di.container.type.InjectionType;
import cvut.fit.di.repository.store.BeanStore;
import cvut.fit.di.repository.store.BeanStoreFactory;

import java.lang.reflect.InvocationTargetException;

/**
 *
 * Hlavni API trida.
 *
 * @author Samuel Butta
 */
public class DIContainer {

    /**
     * Uloziste spravovanych bean.
     */
    private BeanStore beanStore;

    /**
     * Servisni trida, ktera predava
     */
    private Executor executor;

    /**
     * Typ injek
     */
    private final InjectionType injectionType;

    /**
     * Inicializace probiha po zavolani konstruktoru.
     */
    public DIContainer() {
        this.injectionType = InjectionType.SETTER;
        init();
    }


    /**
     *
     * @param injectionType typ injektaze
     */
    public DIContainer(InjectionType injectionType) {
        this.injectionType = injectionType;
        init();
    }

    /**
     * Zakladni inicializacni metoda.
     * Vola buildera, ktery vytvori objektovy graf.
     */
    private void init() {
        executor = new Executor();
        beanStore = BeanStoreFactory.getBeanStore();
    }

    public <T> void addBean(Class<T> beanClass) {
        beanStore.addBean(beanClass);
    }

    public <T> void addBean(Class<T> beanInterface, Class<? extends T> beanImpl)  {
        beanStore.addBean(beanInterface, beanImpl);
    }

    public <T> Object getInstance(Class<T> clazz) throws InvocationTargetException, IllegalAccessException {
        return executor.getInstance(clazz);
    }

    // TODO
    public <T> Object getInstanceByFields(Class<T> clazz) throws InvocationTargetException, IllegalAccessException {
        return executor.getInstanceByFields(clazz);
    }

    // TODO
    public <T> Object getInstanceByConstructor(Class<T> clazz) throws InvocationTargetException, IllegalAccessException {
        return executor.getInstanceByConstructor(clazz);
    }
}
