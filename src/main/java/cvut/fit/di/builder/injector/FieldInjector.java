package cvut.fit.di.builder.injector;

import cvut.fit.di.builder.injector.config.ConfigType;
import cvut.fit.di.exception.service.ServiceIsNotInObjectGraphException;
import cvut.fit.di.graph.ServiceNode;
import cvut.fit.di.repository.entity.Service;
import cvut.fit.di.repository.entity.ServiceScope;

import java.lang.reflect.Field;
import java.util.Set;

/**
 * @author Samuel Butta
 */
public class FieldInjector extends Injector {


    public FieldInjector() {
        super();
    }

    public FieldInjector(ConfigType configType) {
        super();
        this.configType = configType;
    }

    @Override
    public Object getInstance(Class initClass) {
        objectGraph.initSubgraphByNode(initClass);

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
                // a na vsechny jeji zavislosti ve fieldech zavola stejnou metodu
                Set<Field> fields = finder.findInjectedFields(initClass);

                for (Field field : fields) {
                    // TODO pouze u private a protected (pak vratit ?)
                    // nastavit field jako zapisovatelny
                    field.setAccessible(true);
                    Class classType = field.getType();
                    try {
                        field.set(parent, getInstance(classType));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }

                return parent;
            }

        } else {
            throw new ServiceIsNotInObjectGraphException();
        }
    }

}
