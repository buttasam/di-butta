package cvut.fit.di.builder.injector;

import cvut.fit.di.exception.AmbiguousConstructorException;
import cvut.fit.di.graph.ClassNode;
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

    @Override
    public Object getInstance(Class initClass) {
        // TODO
        objectGraph.initNode(initClass);

        // overit zda takova trida existuje v objektovem grafu
        ClassNode node = objectGraph.getNode(initClass);

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
                            return getInstance(p);
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
            // TODO vyhod vyjimku
            return null;
        }
    }

}
