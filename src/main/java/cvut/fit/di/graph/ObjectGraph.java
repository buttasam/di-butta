package cvut.fit.di.graph;

import cvut.fit.di.builder.helper.Finder;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Set;

/**
 * Objektova reprezentace
 */
public class ObjectGraph {


    // vsechny uzli grafu
    HashMap<Class, ClassNode> allNodes;

    // priznak urcuje, zda jiz byl objektovy graf inicializovany
    private boolean isInit;

    /**
     * Utility
     */
    Finder finder;

    public ObjectGraph() {
        this.allNodes = new HashMap<>();
        finder = new Finder();
        isInit = false;
    }


    public ClassNode initNode(Class clazz) {
        // pokusi se najit dany node
        ClassNode node = allNodes.get(clazz);

        // pokud node neexistuje
        if (node == null) {
            // vytvori ho
            node = createNewNode(clazz);
            // najde vsechny jeho zavislosti podle setteru
            Set<Method> setters = finder.findInjectedSetters(clazz);

            for (Method setter : setters) {
                Class<?> paramClass = setter.getParameterTypes()[0];
                node.addChild(initNode(paramClass));
            }
        }

        return node;
    }

    public ClassNode getNode(Class clazz) {
        return allNodes.get(clazz);
    }


    /**
     * Vytvori novy classNode podle tridy
     * a prida ho do mnoziny vsech nodu
     *
     * @param clazz
     * @return
     */
    private ClassNode createNewNode(Class clazz) {
        ClassNode node = new ClassNode(clazz);
        allNodes.put(clazz, node);

        return node;
    }

}
