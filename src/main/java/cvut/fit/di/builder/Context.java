package cvut.fit.di.builder;

import cvut.fit.di.builder.helper.Creator;
import cvut.fit.di.builder.helper.Finder;
import cvut.fit.di.builder.injector.Injector;
import cvut.fit.di.graph.ObjectGraph;
import cvut.fit.di.repository.store.ServiceStore;

/**
 * Trida udruzje kontext, tedy predevsim instanci obejktoveho grafu a uloziste sluzeb.
 * Dale obsahuje pomocne sluzby.
 *
 * @author Samuel Butta
 */
public class Context {


    /**
     * Uloziste sluzeb.
     */
    private ServiceStore serviceStore;

    /**
     * Objektovy graf reprezentujici zavisle tridy.
     */
    private ObjectGraph objectGraph;


    /**
     * Instance injektoru.
     */
    private Injector injector;

    /**
     * Pomocne tridy.
     */
    private Creator creator;
    private Finder finder;


    private Context() {
        // prazdny konstruktor
    }

    public Context(Injector injector) {
        this.injector = injector;

        creator = new Creator();
        finder = new Finder();

        serviceStore = new ServiceStore();
        objectGraph = new ObjectGraph();

        injector.setObjectGraph(objectGraph);
        injector.setServiceStore(serviceStore);
        injector.initObjectGraphAPI();
    }


    /**
     * Na prislusnem injektoru pozada o ziskani instance.
     *
     * @param initClass poradovana trida
     * @param <T>       typovy parametr
     * @return instance
     */
    public <T> T getInstance(Class<T> initClass) {
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
