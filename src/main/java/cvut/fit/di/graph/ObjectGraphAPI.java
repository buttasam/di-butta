package cvut.fit.di.graph;


import cvut.fit.di.graph.dfs.Status;

import java.util.HashMap;
import java.util.Map;

/**
 * API trida pro operace nad objektovym grafem.
 */
public class ObjectGraphAPI {


    private ObjectGraph objectGraph;

    // TODO odstanit
    @Deprecated
    public ObjectGraphAPI() {
        this.objectGraph = ObjectGraphFactory.getObjectGraph();
    }

    public ObjectGraphAPI(ObjectGraph objectGraph) {
        this.objectGraph = objectGraph;
    }

    public void addService(Class clazzInterface, Class clazzImpl) {
        objectGraph.createNewNodeWithImpl(clazzInterface, clazzImpl);
    }


    public void addService(Class clazz) {
        objectGraph.createNewNode(clazz);
    }

    public boolean detectCycle(Class clazz) {

        // mapa informaci navic, potrebnych pro dany algoritmus
        Map<ServiceNode, Status> metaInfo = new HashMap<>();

        // osetreni zda je trida v objektovem grafu
        objectGraph.isNodePresent(clazz);

        ServiceNode initNode = objectGraph.getNode(clazz);

        boolean result = dfs(initNode, metaInfo);

        return result;
    }

    private boolean dfs(ServiceNode root, Map<ServiceNode, Status> metaInfo) {
        if (root == null) {
            return false;
        }

        System.out.println(root.getClazzImpl());
        metaInfo.put(root, Status.OPEN);

        for (ServiceNode n : root.getConstructorChildren()) {
            if (metaInfo.get(n) == null) {
                boolean result = dfs(n, metaInfo);
                if (!result) {
                    metaInfo.put(root, Status.CLOSE);
                }
                return result;
            } else if (metaInfo.get(n) == Status.OPEN) {
                return true;
            }
        }

        metaInfo.put(root, Status.CLOSE);

        return false;
    }

}
