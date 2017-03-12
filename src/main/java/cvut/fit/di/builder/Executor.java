package cvut.fit.di.builder;

import cvut.fit.di.builder.helper.Creator;
import cvut.fit.di.builder.helper.Finder;
import cvut.fit.di.builder.helper.Injector;
import cvut.fit.di.repository.entity.Bean;
import cvut.fit.di.repository.store.BeanStore;
import cvut.fit.di.repository.store.BeanStoreFactory;

import java.lang.reflect.Method;
import java.util.Set;

/**
 * @author Samuel Butta
 */
public class Executor {


    private Creator creator;
    private Finder finder;
    private Injector injector;

    private BeanStore beanStore;

    public Executor() {
        creator = new Creator();
        finder = new Finder();
        injector = new Injector();

        beanStore = BeanStoreFactory.getBeanStore();
    }


    /**
     * Metoda nastavy vsechny zavislosti
     * rekurzicne se hledaji zavislosti podle vstupni tridy
     * @param initClass vstupni trida
     */
    public Object initObjectGraph(Class initClass) {
        // najdi beanu podle initClass

        Bean bean = beanStore.getOrCreateBean(initClass);

        initObjectGraphRecursion(initClass);

        return bean.getInstance();
    }

    public void initObjectGraphRecursion(Class initClass) {

        Bean bean = beanStore.getOrCreateBean(initClass);
        Set<Method> setters = finder.findInjectedSetters(initClass);
        Set<Class<?>> dependencyTypes = finder.findInjectedSettersDependencies(initClass);

        injector.injectSetterDependencies(bean.getInstance(), setters);
        dependencyTypes.forEach(this::initObjectGraphRecursion);
    }


}
