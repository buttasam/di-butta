package cvut.fit.di.builder.injector;

import cvut.fit.di.builder.helper.Creator;
import cvut.fit.di.builder.helper.Finder;
import cvut.fit.di.graph.ObjectGraph;
import cvut.fit.di.repository.store.BeanStore;
import cvut.fit.di.repository.store.BeanStoreFactory;

/**
 * @author Samuel Butta
 */
public abstract class Injector {

    protected Creator creator;
    protected Finder finder;

    protected BeanStore beanStore;

    // objektovy graf reprezentujici zavisle tridy
    protected ObjectGraph objectGraph;

    public Injector() {
        creator = new Creator();
        finder = new Finder();

        beanStore = BeanStoreFactory.getBeanStore();
        objectGraph = new ObjectGraph();
    }

    public abstract Object getInstance(Class initClass);

}
