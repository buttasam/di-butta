package cvut.fit.di.graph;

import cvut.fit.di.builder.helper.Finder;
import cvut.fit.di.exception.AmbiguousConstructorException;
import cvut.fit.di.exception.ServiceIsNotInObjectGraphException;
import cvut.fit.di.exception.service.ServiceHasMoreImplementationsInSamePackage;
import cvut.fit.di.exception.service.ServiceHasNoImplementationInSamePackage;
import cvut.fit.di.util.Validator;
import org.reflections.Reflections;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Trida reprezentujici objektovy graf
 *
 * @author Samuel Butta
 */
public class ObjectGraph {


    /**
     * Vsechny uzli grafu
     * Klic je trida. V pripade rozhrani a implementace je klic rozhrani.
     */
    private HashMap<Class, ServiceNode> allNodes;

    // priznak urcuje, zda jiz byl objektovy graf inicializovany
    private boolean isInit;

    /**
     * Utility
     */
    private Finder finder;

    public ObjectGraph() {
        this.allNodes = new HashMap<>();
        finder = new Finder();
        isInit = false;
    }


    /**
     * Inicializuje podgraf podle vstupni tridy.
     */
    public ServiceNode initSubgraphByNode(Class clazz) {
        // pokusi se najit dany node
        ServiceNode node = allNodes.get(clazz);

        // pokud node neexistuje
        if (node == null) {
            // vytvori ho
            node = createNewNode(clazz);

            // najde vsechny jeho zavislosti podle setteru
            Set<Method> setters = finder.findInjectedSetters(clazz);

            for (Method setter : setters) {
                Class<?> paramClass = setter.getParameterTypes()[0];
                node.addSetterChild(initSubgraphByNode(paramClass));
            }

            // najde vsechny jeho zavislosti podle filedu
            Set<Field> fields = finder.findInjectedFields(clazz);
            for (Field field : fields) {
                node.addFieldChild(initSubgraphByNode(field.getType()));
            }


            // najde vsechny jeho zavislosti podle konstruktoru
            Constructor constructor = finder.findInjectedConstructor(clazz);

            if (constructor != null) {
                Class[] paramTypes = constructor.getParameterTypes();

                Set<ServiceNode> constructorChildren = Arrays.stream(paramTypes).map(this::initSubgraphByNode).collect(Collectors.toSet());

                node.addConstructorChildren(constructorChildren);
            }

        }

        return node;
    }

    public ServiceNode getNode(Class clazz) {
        return allNodes.get(clazz);
    }


    /**
     * Vytvori novy classNode podle tridy
     * a prida ho do mnoziny vsech nodu
     *
     * @param clazz
     * @return
     */
    public synchronized ServiceNode createNewNode(Class clazz) {

        ServiceNode node;
        // pokud je interface
        if (clazz.isInterface()) {

            // najde jeho implementace ve stejnem balicku a musi byt jedna

            Reflections reflections = new Reflections(clazz.getPackage().getName());
            Set<Class<?>> classes = reflections.getSubTypesOf(clazz);

            if (classes.size() == 0) {
                throw new ServiceHasNoImplementationInSamePackage();
            }
            if (classes.size() > 1) {
                throw new ServiceHasMoreImplementationsInSamePackage();
            }

            Class clazzImpl = classes.iterator().next();

            node = new ServiceNode(clazz, clazzImpl);

        } else {
            node = new ServiceNode(clazz);
        }

        allNodes.put(clazz, node);


        return node;
    }


    public synchronized ServiceNode createNewNodeWithImpl(Class clazzInterface, Class clazzImpl) {
        Validator.isClazzImplementationOfInterface(clazzInterface, clazzImpl);

        ServiceNode node = new ServiceNode(clazzInterface, clazzImpl);
        allNodes.put(clazzInterface, node);

        return node;
    }

    /**
     * Package private metoda
     *
     * @return
     */
    HashMap<Class, ServiceNode> getAllNodes() {
        return allNodes;
    }

    /**
     * Overi zda je uzel pro danou tridu pritomny v objektovem grafu.
     * Pokud neni, vyhodi runtime vyjimku.
     */
    public void isNodePresent(Class clazz) {
        if (getNode(clazz) == null) {
            throw new ServiceIsNotInObjectGraphException();
        }
    }

}
