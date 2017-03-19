package cvut.fit.di.builder.injector;

import cvut.fit.di.graph.ClassNode;
import cvut.fit.di.repository.entity.Bean;
import cvut.fit.di.repository.entity.BeanScope;

import java.lang.reflect.Field;
import java.util.Set;

/**
 * @author Samuel Butta
 */
public class FieldInjector extends Injector {

    @Override
    public Object getInstance(Class initClass) {
        // TODO
        objectGraph.initNode(initClass);

        // overit zda takova trida existuje v objektovem grafu
        ClassNode node = objectGraph.getNode(initClass);

        // pokud existuje
        if (node != null) {

            // ziskej nebo vytvor beanu
            Bean bean = beanStore.getOrCreateBean(initClass);

            // pokud je singleton a je jiz inicializovana vrat ji
            if (bean.getBeanScope().equals(BeanScope.SINGLETON) && bean.getSingletonInstance() != null) {
                return bean.getSingletonInstance();
            } else {
                //jinak vytvorit novou
                Object parent = bean.getInstance();
                // a na vsechny jeji zavislosti ve fieldech zavola stejnou metodu
                Set<Field> fields = finder.findInjectedFields(initClass);

                for (Field field : fields) {
                    // TODO pouze u private a protected (pak vratit)
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
            // TODO vyhod vyjimku
            return null;
        }
    }

}
