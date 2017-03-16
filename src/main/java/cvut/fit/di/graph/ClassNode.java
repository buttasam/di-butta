package cvut.fit.di.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Trida reprezentuje jeden uzel objektoveho grafu.
 */
public class ClassNode {

    /**
     * Typ tridy daneho uzlu
     */
    private Class clazz;

    /**
     * Potomci daneho uzlu
     */
    private Set<ClassNode> children;

    public ClassNode(Class clazz) {
        this.clazz = clazz;
        children = new HashSet<>();
    }

    /**
     * Prida potomka
     * @param child
     */
    public void addChild(ClassNode child) {
        children.add(child);
    }

}
