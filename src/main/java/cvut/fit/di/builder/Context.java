package cvut.fit.di.builder;

import cvut.fit.di.builder.helper.Creator;
import cvut.fit.di.builder.helper.Finder;
import cvut.fit.di.builder.injector.Injector;
import cvut.fit.di.exception.ServiceIsNotInObjectGraphException;
import cvut.fit.di.graph.ObjectGraph;
import cvut.fit.di.graph.ObjectGraphFactory;
import cvut.fit.di.repository.store.ServiceStore;
import cvut.fit.di.repository.store.ServiceStoreFactory;

import java.lang.reflect.InvocationTargetException;

/**
 * Pomocna trida ktera udrzuje vsechny potrebe informace
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

    public Context(Injector injector) {
        this.injector = injector;

        creator = new Creator();
        finder = new Finder();

        serviceStore = ServiceStoreFactory.getServiceStore();
        objectGraph = ObjectGraphFactory.getObjectGraph();
    }


    public <T> T getInstance(Class initClass) throws InvocationTargetException, IllegalAccessException, ServiceIsNotInObjectGraphException {
        return injector.getInstance(initClass);
    }

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
}
