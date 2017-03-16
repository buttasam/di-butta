package cvut.fit.di.builder;

import cvut.fit.di.builder.helper.Creator;
import cvut.fit.di.builder.helper.Finder;
import cvut.fit.di.builder.helper.Injector;
import cvut.fit.di.graph.ClassNode;
import cvut.fit.di.graph.ObjectGraph;
import cvut.fit.di.repository.entity.Bean;
import cvut.fit.di.repository.entity.BeanScope;
import cvut.fit.di.repository.store.BeanStore;
import cvut.fit.di.repository.store.BeanStoreFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
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

    // objektovy graf reprezentujici zavisle tridy
    private ObjectGraph objectGraph;

    public Executor() {
        creator = new Creator();
        finder = new Finder();
        injector = new Injector();

        beanStore = BeanStoreFactory.getBeanStore();
        objectGraph = new ObjectGraph();
    }


    /**
     * Metoda nastavy vsechny zavislosti
     * rekurzicne se hledaji zavislosti podle vstupni tridy
     * @param initClass vstupni trida
     */
    public void initObjectGraph(Class initClass) {

    }


    public Object getInstance(Class initClass) throws InvocationTargetException, IllegalAccessException {
        objectGraph.initNode(initClass);
        // TODO
        Finder finder = new Finder();

        // overit zda takova trida existuje v objektovem grafu
        ClassNode node = objectGraph.getNode(initClass);

        // pokud existuje
        if(node != null) {

            // ziskej nebo vytvor beanu
            Bean bean = beanStore.getOrCreateBean(initClass);

            // pokud je singleton a je jiz inicializovana vrat ji
            if(bean.getBeanScope().equals(BeanScope.SINGLETON) && bean.getSingletonInstance() != null) {
                return bean.getSingletonInstance();
            } else {
                //jinak vytvorit novou
                Object parent = bean.getInstance();
                // a na vsechny jeji zavislosti v setterech zavola stejnou metodu
                Set<Method> setters = finder.findInjectedSetters(initClass);

                for(Method setter : setters) {
                    Object setterParam = getInstance(setter.getParameterTypes()[0]);
                    setter.invoke(parent, setterParam);
                }

                return parent;
            }

        } else {
           // TODO vyhod vyjimku
            return null;
        }
    }
    public Object getInstanceByFields(Class initClass) throws InvocationTargetException, IllegalAccessException {
        objectGraph.initNode(initClass);
        // TODO
        Finder finder = new Finder();

        // overit zda takova trida existuje v objektovem grafu
        ClassNode node = objectGraph.getNode(initClass);

        // pokud existuje
        if(node != null) {

            // ziskej nebo vytvor beanu
            Bean bean = beanStore.getOrCreateBean(initClass);

            // pokud je singleton a je jiz inicializovana vrat ji
            if(bean.getBeanScope().equals(BeanScope.SINGLETON) && bean.getSingletonInstance() != null) {
                return bean.getSingletonInstance();
            } else {
                //jinak vytvorit novou
                Object parent = bean.getInstance();
                // a na vsechny jeji zavislosti ve fieldech zavola stejnou metodu
                Set<Field> fields = finder.findInjectedFields(initClass);

                for(Field field : fields) {
                    // TODO pouze u private a protected (pak vratit)
                    // nastavit field jako zapisovatelny
                    field.setAccessible(true);
                    Class classType = field.getType();
                    field.set(parent, getInstanceByFields(classType));
                }

                return parent;
            }

        } else {
           // TODO vyhod vyjimku
            return null;
        }
    }


}
