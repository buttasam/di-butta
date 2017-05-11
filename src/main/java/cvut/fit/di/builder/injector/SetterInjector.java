package cvut.fit.di.builder.injector;

import cvut.fit.di.builder.injector.config.ConfigType;
import cvut.fit.di.exception.service.ServiceIsNotInObjectGraphException;
import cvut.fit.di.exception.injector.InjectorException;
import cvut.fit.di.graph.ServiceNode;
import cvut.fit.di.repository.entity.Service;
import cvut.fit.di.repository.entity.ServiceScope;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;

/**
 * @author Samuel Butta
 */
public class SetterInjector extends Injector {


    public SetterInjector() {
        super();
    }

    public SetterInjector(ConfigType configType) {
        super();
        this.configType = configType;
    }


    @Override
    public Object getInstance(Class initClass) {

        this.initSubgraphByIntrospection(initClass);

        // overit zda takova trida existuje v objektovem grafu
        ServiceNode node = objectGraph.getNode(initClass);

        // pokud existuje
        if (node != null) {

            // ziskej nebo vytvor service
            Service service = serviceStore.getOrCreateService(node);

            // pokud je singleton a je jiz inicializovana vrat ji
            if (service.getServiceScope().equals(ServiceScope.SINGLETON) && service.getSingletonInstance() != null) {
                return service.getSingletonInstance();
            } else {
                //jinak vytvorit novou
                Object parent = service.getInstance();
                // a na vsechny jeji zavislosti v setterech zavola stejnou metodu
                Set<Method> setters = finder.findInjectedSetters(initClass);

                for (Method setter : setters) {
                    Object setterParam = getInstance(setter.getParameterTypes()[0]);
                    try {
                        setter.invoke(parent, setterParam);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        throw new InjectorException(e);
                    }
                }

                return parent;
            }

        } else {
            throw new ServiceIsNotInObjectGraphException();
        }
    }
}
