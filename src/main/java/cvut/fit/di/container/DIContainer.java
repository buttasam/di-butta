package cvut.fit.di.container;

import cvut.fit.di.builder.Context;
import cvut.fit.di.builder.injector.Injector;
import cvut.fit.di.builder.injector.SetterInjector;
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
    private Context context;

    /**
     * Injektor, diky polymorfismu rozlisi o jaky typ injektaze se jedna.
     */
    private final Injector injector;

    /**
     * Inicializace probiha po zavolani konstruktoru.
     * Vychozi injektor je nastaven na SetterInjector
     */
    public DIContainer() {
        this.injector = new SetterInjector();
        init();
    }


    /**
     *
     * @param injector
     */
    public DIContainer(Injector injector) {
        this.injector = injector;
        init();
    }

    /**
     * Zakladni inicializacni metoda.
     * Vola buildera, ktery vytvori objektovy graf.
     */
    private void init() {
        context = new Context(injector);
        beanStore = BeanStoreFactory.getBeanStore();
    }

    public <T> void addBean(Class<T> beanClass) {
        beanStore.addBean(beanClass);
    }

    public <T> void addBean(Class<T> beanInterface, Class<? extends T> beanImpl)  {
        beanStore.addBean(beanInterface, beanImpl);
    }

    public <T> Object getInstance(Class<T> clazz) throws InvocationTargetException, IllegalAccessException {
        return context.getInstance(clazz);
    }
    
}
