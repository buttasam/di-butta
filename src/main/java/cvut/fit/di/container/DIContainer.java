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
}
