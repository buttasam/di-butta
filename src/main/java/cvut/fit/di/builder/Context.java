package cvut.fit.di.builder;

import cvut.fit.di.builder.helper.Creator;
import cvut.fit.di.builder.helper.Finder;
import cvut.fit.di.builder.injector.Injector;
import cvut.fit.di.exception.ServiceIsNotInObjectGraphException;
import cvut.fit.di.graph.ObjectGraph;
import cvut.fit.di.repository.store.ServiceStore;
import cvut.fit.di.repository.store.ServiceStoreFactory;

import java.lang.reflect.InvocationTargetException;

/**
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
        objectGraph = new ObjectGraph();
    }


    public <T> T getInstance(Class initClass) throws InvocationTargetException, IllegalAccessException, ServiceIsNotInObjectGraphException {
        return injector.getInstance(initClass);
    }

}
