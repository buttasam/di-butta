package cvut.fit.di.builder;

import cvut.fit.di.builder.helper.Creator;
import cvut.fit.di.builder.helper.Finder;
import cvut.fit.di.builder.injector.Injector;
import cvut.fit.di.graph.ObjectGraph;
import cvut.fit.di.repository.store.BeanStore;
import cvut.fit.di.repository.store.BeanStoreFactory;

import java.lang.reflect.InvocationTargetException;

/**
 * @author Samuel Butta
 */
public class Context {


    private Creator creator;
    private Finder finder;

    private BeanStore beanStore;

    // objektovy graf reprezentujici zavisle tridy
    private ObjectGraph objectGraph;

    private Injector injector;

    public Context(Injector injector) {
        this.injector = injector;

        creator = new Creator();
        finder = new Finder();

        beanStore = BeanStoreFactory.getBeanStore();
        objectGraph = new ObjectGraph();
    }


    public Object getInstance(Class initClass) throws InvocationTargetException, IllegalAccessException {
        return injector.getInstance(initClass);
    }

}
