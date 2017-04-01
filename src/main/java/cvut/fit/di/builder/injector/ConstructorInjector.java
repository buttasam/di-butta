package cvut.fit.di.builder.injector;

import cvut.fit.di.builder.injector.cofig.ConfigType;
import cvut.fit.di.exception.AmbiguousConstructorException;
import cvut.fit.di.exception.ServiceIsNotInObjectGraphException;
import cvut.fit.di.graph.ServiceNode;
import cvut.fit.di.repository.entity.Service;
import cvut.fit.di.repository.entity.ServiceScope;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Samuel Butta
 */
public class ConstructorInjector extends Injector {


    public ConstructorInjector() {
        super();
    }

    public ConstructorInjector(ConfigType configType) {
        super();
        this.configType = configType;
    }


    @Override
    public Object getInstance(Class initClass) throws ServiceIsNotInObjectGraphException {

        // inicializace grafu (podgrafu) introspekci
        if(configType.equals(ConfigType.INTROSPECTION)) {
            objectGraph.initSubgraphByNode(initClass);
        }

        // overit zda takova trida existuje v objektovem grafu
        ServiceNode node = objectGraph.getNode(initClass);

        // pokud existuje
        if (node != null) {

            // ziskej nebo vytvor service
            Service service = serviceStore.getOrCreateService(initClass);

            // pokud je singleton a je jiz inicializovana vrat ji
            if (service.getServiceScope().equals(ServiceScope.SINGLETON) && service.getSingletonInstance() != null) {
                return service.getSingletonInstance();
            } else {
                // konstruktor s anotaci inject
                Constructor constructor = null;
                try {
                    constructor = finder.findInjectedConstructor(initClass);
                } catch (AmbiguousConstructorException e) {
                    e.printStackTrace();
                }

                List<Object> params = new ArrayList<>();

                if(constructor != null) {
                    // vsechny typy jeho argumenty
                    Class[] paramTypes = constructor.getParameterTypes();

                    // vytvor vsechny argumenty

                    params = Arrays.stream(paramTypes).map(p -> {
                        try {
                            return getInstance(p);
                        } catch (ServiceIsNotInObjectGraphException e) {
                            e.printStackTrace();
                            return null;
                        }
                    }).collect(Collectors.toList());
                }

                if(params.size() != 0) {
                    try {
                        return constructor.newInstance(params.toArray());
                    } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                        return null;
                    }
                } else {
                    return creator.createNewInstance(initClass);
                }
            }

        } else {
            throw new ServiceIsNotInObjectGraphException();
        }
    }

}
