package cvut.fit.di.builder.injector;

import cvut.fit.di.builder.injector.config.ConfigType;
import cvut.fit.di.exception.CircularDependencyFoundException;
import cvut.fit.di.exception.service.ServiceIsNotInObjectGraphException;
import cvut.fit.di.graph.ServiceNode;
import cvut.fit.di.repository.entity.Service;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Injektor pro konstruktorovou injektaz.
 * Pokud dojde k nalezni cyklu, vyhodi vyjimku.
 *
 * @author Samuel Butta
 */
public class NotCycleConstructorInjector extends Injector {

    /**
     * priznak, zda je byl hledan cyklus
     */
    private boolean cycleWasSearched = false;

    public NotCycleConstructorInjector() {
        super();
    }

    public NotCycleConstructorInjector(ConfigType configType) {
        super();
        this.configType = configType;
    }


    @Override
    public <T> T getInstance(Class<T> initClass) throws ServiceIsNotInObjectGraphException {

        // inicializace grafu (podgrafu) introspekci
        initSubgraphByIntrospection(initClass);

        // overit zda takova trida existuje v objektovem grafu
        ServiceNode node = objectGraph.getNode(initClass);

        if (!cycleWasSearched) {
            if (objectGraphAPI.detectConstructorCycle(initClass)) {
                throw new CircularDependencyFoundException();
            }

            cycleWasSearched = false;
        }

        // pokud existuje
        if (node != null) {

            // ziskej nebo vytvor service
            Service service = serviceStore.getOrCreateService(node);

            // pokud je singleton a je jiz inicializovana vrat ji
            if (service.singletonAvailable()) {
                return (T) service.getSingletonInstance();
            } else {
                // konstruktor s anotaci inject
                Constructor constructor = finder.findInjectedConstructor(node.getClazzImpl());

                List<Object> params = new ArrayList<>();

                if (constructor != null) {
                    // vsechny typy jeho argumenty
                    Class[] paramTypes = constructor.getParameterTypes();

                    // vytvor vsechny argumenty
                    params = Arrays.stream(paramTypes).map(p -> {
                        try {
                            ServiceNode paramNode = objectGraph.getNode(p);
                            return getInstance(paramNode.getClazzImpl());
                        } catch (ServiceIsNotInObjectGraphException e) {
                            e.printStackTrace();
                            return null;
                        }
                    }).collect(Collectors.toList());
                }

                return (T) service.getInstance(constructor, params);
            }

        } else {
            throw new ServiceIsNotInObjectGraphException();
        }
    }

}
