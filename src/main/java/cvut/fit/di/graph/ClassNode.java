package cvut.fit.di.graph;

import java.util.HashSet;
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

    /**
     * Potomci injectovani setterem
     */
    private Set<ClassNode> setterChildren;


    /**
     * Potomci injectovani fieldem
     */
    private Set<ClassNode> fieldChildren;


    /**
     * Potomci injectovani konstrutorem
     */
    private Set<ClassNode> constructorChildren;

    public ClassNode(Class clazz) {
        this.clazz = clazz;

        children = new HashSet<>();
        setterChildren = new HashSet<>();
        fieldChildren = new HashSet<>();
        constructorChildren = new HashSet<>();
    }

    /**
     * Prida potomka
     *
     * @param child
     */
    @Deprecated
    public void addChild(ClassNode child) {
        children.add(child);
    }

    public void addSetterChild(ClassNode child) {
        setterChildren.add(child);
    }

    public void addFieldChild(ClassNode child) {
        fieldChildren.add(child);
    }

    public void addConstructorChild(ClassNode child) {
        constructorChildren.add(child);
    }

    public void addConstructorChildren(Set<ClassNode> children) {
        constructorChildren.addAll(children);
    }

    /**
     * Vraci pocet pro setter injektaz
     * @return pocet potomku
     */
    public int setterChildrenSize() {
        return setterChildren.size();
    }

    /**
     * Vraci pocet pro field injektaz
     * @return pocet potomku
     */
    public int fieldChildrenSize() {
        return fieldChildren.size();
    }

    /**
     * Vraci pocet pro constructor injektaz
     * @return pocet potomku
     */
    public int constructorChildrenSize() {
        return constructorChildren.size();
    }

}
