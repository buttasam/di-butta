package cvut.fit.di.graph;

import java.util.HashMap;
import java.util.List;

/**
 * Trida reprezentuje jeden uzel objektoveho grafu.
 */
public class ClassNode {

    Class clazz;

    List<ClassNode> children;

    public ClassNode(Class clazz) {
        this.clazz = clazz;
    }

    public void setChildren(List<ClassNode> children) {
        this.children = children;
    }

}
