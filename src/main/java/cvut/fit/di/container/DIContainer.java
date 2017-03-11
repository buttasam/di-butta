package cvut.fit.di.container;

import cvut.fit.di.repository.store.BeanStore;

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

    }

    public <T> void addBean(Class<T> beanClass) {
        beanStore.addBean(beanClass);
    }

    public <T> void addBean(Class<T> beanInterface, Class<? extends T> beanImpl)  {
        beanStore.addBean(beanInterface, beanImpl);
    }
}
