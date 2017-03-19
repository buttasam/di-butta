package cvut.fit.di.builder;

import cvut.fit.di.builder.helper.Creator;
import cvut.fit.di.builder.helper.Finder;
import cvut.fit.di.builder.injector.ConstructorInjector;
import cvut.fit.di.builder.injector.FieldInjector;
import cvut.fit.di.builder.injector.Injector;
import cvut.fit.di.builder.injector.SetterInjector;
import cvut.fit.di.container.type.InjectionType;
import cvut.fit.di.graph.ObjectGraph;
import cvut.fit.di.repository.store.BeanStore;
import cvut.fit.di.repository.store.BeanStoreFactory;

import java.lang.reflect.InvocationTargetException;

/**
 * @author Samuel Butta
 */
public class Executor {


    private Creator creator;
    private Finder finder;

    private BeanStore beanStore;

    // objektovy graf reprezentujici zavisle tridy
    private ObjectGraph objectGraph;

    private Injector injector;

    public Executor(InjectionType injectionType) {
        creator = new Creator();
        finder = new Finder();

        beanStore = BeanStoreFactory.getBeanStore();
        objectGraph = new ObjectGraph();

        initInjectorByType(injectionType);
    }


    private void initInjectorByType(InjectionType injectionType) {
        switch (injectionType) {
            case FIELD:
                this.injector = new FieldInjector();
                break;
            case SETTER:
                this.injector = new SetterInjector();
                break;
            case CONSTRUCTOR:
                this.injector = new ConstructorInjector();
                break;
        }
    }


    public Object getInstance(Class initClass) throws InvocationTargetException, IllegalAccessException {
        return injector.getInstance(initClass);
    }

}
