package cvut.fit.di.builder.injector;

import cvut.fit.di.builder.helper.Creator;
import cvut.fit.di.builder.helper.Finder;
import cvut.fit.di.builder.injector.config.ConfigType;
import cvut.fit.di.exception.service.ServiceIsNotInObjectGraphException;
import cvut.fit.di.graph.ObjectGraph;
import cvut.fit.di.graph.ObjectGraphAPI;
import cvut.fit.di.graph.ObjectGraphFactory;
import cvut.fit.di.repository.store.ServiceStore;
import cvut.fit.di.repository.store.ServiceStoreFactory;

/**
 * @author Samuel Butta
 */
public abstract class Injector {

    protected Creator creator;
    protected Finder finder;

    protected ServiceStore serviceStore;

    /**
     * Typ konfigurace objektoveho grafu.
     */
    protected ConfigType configType;

    // objektovy graf reprezentujici zavisle tridy
    protected ObjectGraph objectGraph;

    // API dostupne pro vsechny potomky Injectoru
    protected ObjectGraphAPI objectGraphAPI;

    public Injector() {
        creator = new Creator();
        finder = new Finder();

        serviceStore = ServiceStoreFactory.getServiceStore();
        objectGraph = ObjectGraphFactory.getObjectGraph();

        configType = ConfigType.INTROSPECTION;

        objectGraphAPI = new ObjectGraphAPI(objectGraph);
    }

    public abstract <T> T getInstance(Class<T> initClass) throws ServiceIsNotInObjectGraphException;


    /**
     * Rekurzivni metoda nastavi podgraf pokud je
     * configType nastaven na hodnotu ConfigType.INTROSPECTION.
     *
     * @param initClass
     */
    public void initSubgraphByIntrospection(Class initClass) {
        // inicializace grafu (podgrafu) introspekci
        if(configType.equals(ConfigType.INTROSPECTION)) {
            objectGraph.initSubgraphByNode(initClass);
        }
    }

    public void setConfigType(ConfigType configType) {
        this.configType = configType;
    }

}
