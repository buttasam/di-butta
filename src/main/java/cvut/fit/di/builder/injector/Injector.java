package cvut.fit.di.builder.injector;

import cvut.fit.di.builder.helper.Creator;
import cvut.fit.di.builder.helper.Finder;
import cvut.fit.di.builder.injector.config.ConfigType;
import cvut.fit.di.exception.service.ServiceIsNotInObjectGraphException;
import cvut.fit.di.graph.ObjectGraph;
import cvut.fit.di.graph.ObjectGraphAPI;
import cvut.fit.di.repository.store.ServiceStore;

/**
 * Abstraktni predek vsech injektoru.
 * Pro pripadnou vlastni implementaci je nutne prekryt a naimplementovat metodu getInstance.
 *
 * @author Samuel Butta
 */
public abstract class Injector {


    /**
     * Uloziste sluzeb.
     */
    protected ServiceStore serviceStore;

    /**
     * Typ konfigurace objektoveho grafu.
     */
    protected ConfigType configType;

    /**
     * Objektovy graf reprezentujici zavisle tridy.
     */
    protected ObjectGraph objectGraph;

    /**
     * API dostupne pro vsechny potomky Injectoru.
     */
    protected ObjectGraphAPI objectGraphAPI;

    /**
     * Pomocne sluzby.
     */
    protected Creator creator;
    protected Finder finder;

    public Injector() {
        creator = new Creator();
        finder = new Finder();

        configType = ConfigType.INTROSPECTION;
    }

    /**
     * Rekurzivni metoda, v ktere dojde k inicializaci objektoveho grafu a inicializaci objektu.
     *
     * @param initClass typ pozadovane instance
     * @param <T>       typovy parametr
     * @return instance daneho typu
     */
    public abstract <T> T getInstance(Class<T> initClass);


    /**
     * Rekurzivni metoda nastavi podgraf pokud je
     * configType nastaven na hodnotu ConfigType.INTROSPECTION.
     *
     * @param initClass typ vstupni tridy
     */
    public void initSubgraphByIntrospection(Class initClass) {
        // inicializace grafu (podgrafu) introspekci
        if (configType.equals(ConfigType.INTROSPECTION)) {
            objectGraph.initSubgraphByNode(initClass);
        }
    }

    /**
     * Nastavi typ konfigurace.
     *
     * @param configType typ konfigurace
     */
    public void setConfigType(ConfigType configType) {
        this.configType = configType;
    }

    /**
     * Nastavi instanci uloziste.
     *
     * @param serviceStore instance uloziste
     */
    public void setServiceStore(ServiceStore serviceStore) {
        this.serviceStore = serviceStore;
    }

    /**
     * Nastavi objektovy graf
     *
     * @param objectGraph instance objektoveho grafu
     */
    public void setObjectGraph(ObjectGraph objectGraph) {
        this.objectGraph = objectGraph;
    }

    /**
     * Inicializuje API nad objektovym grafem.
     */
    public void initObjectGraphAPI() {
        this.objectGraphAPI = new ObjectGraphAPI(objectGraph);
    }
}
