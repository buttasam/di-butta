package cvut.fit.di.graph;

import cvut.fit.di.builder.helper.Finder;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Created by samik on 14.3.17.
 */
public class ObjectGraph {


    // vsechny uzli grafu
    HashMap<Class, ClassNode> allNodes;


    public ObjectGraph() {
        this.allNodes = new HashMap<>();
    }


    public ClassNode initNode(Class clazz) {
        // pokusi se najit dany node
        ClassNode node = allNodes.get(clazz);


        // pokud node neexistuje
        if(node == null) {
            List<ClassNode> children = new ArrayList<>();

            node = new ClassNode(clazz);
            allNodes.put(clazz, node);

            Finder finder = new Finder();

            // najde vsechny jeho zavislosti podle setteru
            Set<Method> setters = finder.findInjectedSetters(clazz);

            for(Method setter : setters) {
                Class<?> paramClass = setter.getParameterTypes()[0];

                children.add(initNode(paramClass));
            }

            node.setChildren(children);
        }

        return node;
    }

    public ClassNode getNode(Class clazz) {

        return allNodes.get(clazz);
    }

}
