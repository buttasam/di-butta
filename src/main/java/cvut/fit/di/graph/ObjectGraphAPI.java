package cvut.fit.di.graph;


import com.sun.org.apache.xpath.internal.NodeSet;

import java.util.HashSet;

/**
 * API trida pro operace nad objektovym grafem.
 */
public class ObjectGraphAPI {


    private ObjectGraph objectGraph;

    public ObjectGraphAPI() {
        this.objectGraph = ObjectGraphFactory.getObjectGraph();
    }

    public void addClazzWithImpl(Class clazzInterface, Class clazzImpl) {
        objectGraph.createNewNodeWithImpl(clazzInterface, clazzImpl);
    }


    public void addClazzWithoutImpl(Class clazz) {
        objectGraph.createNewNode(clazz);
    }

    public void detectCycle(Class clazz) {

        HashSet<NodeSet> visited = new HashSet<>();

        // osetreni zda je trida v objektovem grafu
        objectGraph.isNodePresent(clazz);

        ServiceNode initNode = objectGraph.getNode(clazz);

        dfs(initNode);

        // vycisteni
        cleanObjectGraphFlags();
    }

    public void dfs(ServiceNode root)
    {
        //Avoid infinite loops
        if(root == null) return;

        System.out.print(root.getClazzImpl());
        root.setVisited(true);

        //for every child
        for(ServiceNode n : root.getConstructorChildren())
        {
            //if childs state is not visited then recurse
            if(!n.isVisited())
            {
                dfs(n);
            }
        }
    }


    /**
     * Nastavy priznaky s kterymi pracuji algoritmy na pocatecni hodnotu.
     * TODO asi blbost, nemelo by to menis stav ServiceNode
     */
    private void cleanObjectGraphFlags() {
        objectGraph.getAllNodes().forEach((k, v) -> v.setDfsStatus(null));
        objectGraph.getAllNodes().forEach((k, v) -> v.setVisited(false));
    }

}
