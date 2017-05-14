package cvut.fit.di.graph;

import cvut.fit.di.anotation.scope.Prototype;
import cvut.fit.di.exception.service.UndefinedScopeOfServiceClassException;
import cvut.fit.di.graph.dfs.Status;
import cvut.fit.di.repository.entity.ServiceScope;

import javax.inject.Singleton;
import java.util.HashSet;
import java.util.Set;

/**
 * Trida reprezentuje jeden uzel objektoveho grafu.
 *
 * @author Samuel Butta
 */
public class ServiceNode {

    /**
     * Typ tridy daneho uzlu
     */
    private Class clazzInterface;

    /**
     * Typ tridy daneho uzlu
     */
    private Class clazzImpl;

    /**
     * Potomci injectovani setterem
     */
    private Set<ServiceNode> setterChildren;


    /**
     * Potomci injectovani fieldem
     */
    private Set<ServiceNode> fieldChildren;


    /**
     * Doba zivota service
     */
    private ServiceScope serviceScope;

    /**
     * Potomci injectovani konstrutorem
     */
    private Set<ServiceNode> constructorChildren;

    // Priznamky potrebne pro operace nad grafem

    /**
     * Priznak, zda byl uzel navstiven.
     */
    private Status dfsStatus;
    private boolean visited;

    public ServiceNode(Class clazzImpl) {
        this.clazzImpl = clazzImpl;

        init(clazzImpl);
    }

    public ServiceNode(Class clazzInterface, Class clazzImpl) {
        this.clazzInterface = clazzInterface;
        this.clazzImpl = clazzImpl;

        init(clazzImpl);
    }


    private void init(Class clazzImpl) {
        setterChildren = new HashSet<>();
        fieldChildren = new HashSet<>();
        constructorChildren = new HashSet<>();

        findAndSetServiceScope(clazzImpl);
    }

    public void addSetterChild(ServiceNode child) {
        setterChildren.add(child);
    }

    public void addFieldChild(ServiceNode child) {
        fieldChildren.add(child);
    }

    public void addConstructorChild(ServiceNode child) {
        constructorChildren.add(child);
    }

    public void addConstructorChildren(Set<ServiceNode> children) {
        constructorChildren.addAll(children);
    }

    /**
     * Vraci pocet pro setter injektaz
     *
     * @return pocet potomku
     */
    public int setterChildrenSize() {
        return setterChildren.size();
    }

    /**
     * Vraci pocet pro field injektaz
     *
     * @return pocet potomku
     */
    public int fieldChildrenSize() {
        return fieldChildren.size();
    }

    /**
     * Vraci pocet pro constructor injektaz
     *
     * @return pocet potomku
     */
    public int constructorChildrenSize() {
        return constructorChildren.size();
    }


    public ServiceScope getServiceScope() {
        return serviceScope;
    }


    public Set<ServiceNode> getSetterChildren() {
        return setterChildren;
    }

    public Set<ServiceNode> getFieldChildren() {
        return fieldChildren;
    }

    public Set<ServiceNode> getConstructorChildren() {
        return constructorChildren;
    }

    /**
     * Podle anotace urci scope service.
     * Pokud neobsahuje scope vyhodi chybu.
     *
     * @param clazz
     */
    private void findAndSetServiceScope(Class clazz) {
        if (clazz.isAnnotationPresent(Singleton.class)) {
            serviceScope = ServiceScope.SINGLETON;
        } else if (clazz.isAnnotationPresent(Prototype.class)) {
            serviceScope = ServiceScope.PROTOTYPE;
        } else {
            throw new UndefinedScopeOfServiceClassException(clazz);
        }
    }

    public Status getDfsStatus() {
        return dfsStatus;
    }

    public void setDfsStatus(Status dfsStatus) {
        this.dfsStatus = dfsStatus;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public Class getClazzInterface() {
        return clazzInterface;
    }

    public void setClazzInterface(Class clazzInterface) {
        this.clazzInterface = clazzInterface;
    }

    public Class getClazzImpl() {
        return clazzImpl;
    }

    public void setClazzImpl(Class clazzImpl) {
        this.clazzImpl = clazzImpl;
    }
}
