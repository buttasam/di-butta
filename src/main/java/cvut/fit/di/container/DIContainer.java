package cvut.fit.di.container;

import cvut.fit.di.builder.Executor;
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

    private Executor executor;

    /**
     * Inicializace probiha po zavolani konstruktoru.
     */
    public DIContainer() {
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

    public Object getInstance(Class clazz) throws InvocationTargetException, IllegalAccessException {
        return executor.getInstance(clazz);
    }
}
