package cvut.fit.di.builder.injector;

import cvut.fit.di.builder.injector.config.ConfigType;
import cvut.fit.di.exception.service.ServiceIsNotInObjectGraphException;
import cvut.fit.di.graph.ServiceNode;
import cvut.fit.di.repository.entity.Service;

import java.lang.reflect.Field;
import java.util.Set;

/**
 * Injektor pro injektaz pomoci atributu.
 * Objektovy graf muze obsahovat cyklickou zavislost.
 * Atributy mohou mit libovolny modifikator pristupu.
 *
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

    /**
     * {@inheritDoc}
     */
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
            if (service.singletonAvailable()) {
                return service.getSingletonInstance();
            } else {
                //jinak vytvorit novou
                Object parent = service.getInstance();
                // a na vsechny jeji zavislosti ve fieldech zavola stejnou metodu
                Set<Field> fields = finder.findInjectedFields(initClass);

                for (Field field : fields) {
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
