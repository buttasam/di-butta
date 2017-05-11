package cvut.fit.di.builder;

import cvut.fit.di.builder.helper.Creator;
import cvut.fit.di.builder.helper.Finder;
import cvut.fit.di.builder.injector.Injector;
import cvut.fit.di.graph.ObjectGraph;
import cvut.fit.di.graph.ObjectGraphFactory;
import cvut.fit.di.repository.store.ServiceStore;
import cvut.fit.di.repository.store.ServiceStoreFactory;

import java.lang.reflect.InvocationTargetException;

/**
 * Pomocna trida ktera udrzuje potrebe informace
 * spojene s objektovym grafem a kontejnerem.
 *
 * @author Samuel Butta
 */
public class Context {


    private Creator creator;
    private Finder finder;

    private ServiceStore serviceStore;

    // objektovy graf reprezentujici zavisle tridy
    private ObjectGraph objectGraph;

    private Injector injector;

    private Context() {
        // prazdny konstruktor
    }

    public Context(Injector injector) {
        this.injector = injector;

        creator = new Creator();
        finder = new Finder();

        serviceStore = ServiceStoreFactory.getServiceStore();
        objectGraph = ObjectGraphFactory.getObjectGraph();
    }


    /**
     * Na prislusnem injektoru pozada o vytvoreni instance.
     *
     * @param initClass poradovana trida
     * @param <T>       typovy parametr
     * @return instance
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public <T> T getInstance(Class<T> initClass) throws InvocationTargetException, IllegalAccessException {
        return injector.getInstance(initClass);
    }

    /**
     * Vrati instanci uloziste sluzeb
     *
     * @return uloziste sluzeb
     */
    public ServiceStore getServiceStore() {
        return serviceStore;
    }

    /**
     * Vrati instanci objektoveho grafu
     *
     * @return objektovy graf
     */
    public ObjectGraph getObjectGraph() {
        return objectGraph;
    }

    /**
     * Vrati instanci Creator
     *
     * @return creator
     */
    public Creator getCreator() {
        return creator;
    }

    /**
     * Vrati instanci Finder
     *
     * @return finder
     */
    public Finder getFinder() {
        return finder;
    }

    /**
     * Vrati instanci Injector
     *
     * @return injector
     */
    public Injector getInjector() {
        return injector;
    }
}
