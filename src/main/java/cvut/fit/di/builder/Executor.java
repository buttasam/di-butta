package cvut.fit.di.builder;

import cvut.fit.di.builder.helper.Creator;
import cvut.fit.di.builder.helper.Finder;
import cvut.fit.di.builder.injector.FieldInjector;
import cvut.fit.di.builder.injector.Injector;
import cvut.fit.di.builder.injector.SetterInjector;
import cvut.fit.di.container.type.InjectionType;
import cvut.fit.di.exception.AmbiguousConstructorException;
import cvut.fit.di.graph.ClassNode;
import cvut.fit.di.graph.ObjectGraph;
import cvut.fit.di.repository.entity.Bean;
import cvut.fit.di.repository.entity.BeanScope;
import cvut.fit.di.repository.store.BeanStore;
import cvut.fit.di.repository.store.BeanStoreFactory;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Samuel Butta
 */
public class Executor {


    private Creator creator;
    private Finder finder;

    private BeanStore beanStore;

    // objektovy graf reprezentujici zavisle tridy
    private ObjectGraph objectGraph;

    private Injector injector;

    public Executor(InjectionType injectionType) {
        creator = new Creator();
        finder = new Finder();

        beanStore = BeanStoreFactory.getBeanStore();
        objectGraph = new ObjectGraph();

        initInjectorByType(injectionType);
    }


    private void initInjectorByType(InjectionType injectionType) {
        switch (injectionType) {
            case FIELD:
                this.injector = new FieldInjector();
                break;
            case SETTER:
                this.injector = new SetterInjector();
                break;
            case CONSTRUCTOR:
                this.injector = new FieldInjector();
                break;
        }
    }


    public Object getInstance(Class initClass) throws InvocationTargetException, IllegalAccessException {
        return injector.getInstance(initClass);
    }

    public Object getInstanceByFields(Class initClass) throws InvocationTargetException, IllegalAccessException {
        objectGraph.initNode(initClass);
        // TODO
        Finder finder = new Finder();

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
                    field.set(parent, getInstanceByFields(classType));
                }

                return parent;
            }

        } else {
            // TODO vyhod vyjimku
            return null;
        }
    }


    public Object getInstanceByConstructor(Class initClass) throws InvocationTargetException, IllegalAccessException, AmbiguousConstructorException, InstantiationException {
        objectGraph.initNode(initClass);
        // TODO
        Finder finder = new Finder();

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
                // konstruktor s anotaci inject
                Constructor constructor = finder.findInjectedConstructor(initClass);

                List<Object> params = new ArrayList<>();

                if(constructor != null) {
                    // vsechny typy jeho argumenty
                    Class[] paramTypes = constructor.getParameterTypes();

                    // vytvor vsechny argumenty

                    params = Arrays.stream(paramTypes).map(p -> {
                        try {
                            return getInstanceByConstructor(p);
                        } catch (InvocationTargetException | IllegalAccessException | AmbiguousConstructorException | InstantiationException e) {
                            e.printStackTrace();
                            return null;
                        }
                    }).collect(Collectors.toList());
                }

                if(params.size() != 0) {
                    return constructor.newInstance(params.toArray());
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
