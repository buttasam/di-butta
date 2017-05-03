package cvut.fit.di.graph;


import cvut.fit.di.graph.dfs.Status;

import java.util.HashMap;
import java.util.Map;

/**
 * API trida pro operace nad objektovym grafem.
 *
 * @author Samuel Butta
 */
public class ObjectGraphAPI {


    /**
     * Instance objektoveho grafu
     */
    private ObjectGraph objectGraph;


    /**
     * Prazdny konstruktor zamezi vytvoreni
     * instance bez objektoveho grafu
     */
    private ObjectGraphAPI() {
        // prazdny konstruktor
    }

    /**
     * Konstruktor s inicializaci objektoveho grafu
     *
     * @param objectGraph objektovy graf
     */
    public ObjectGraphAPI(ObjectGraph objectGraph) {
        this.objectGraph = objectGraph;
    }


    /**
     * Pridani sluzby do objektoveho grafu.
     *
     * @param clazz typ tridy
     */
    public void addService(Class clazz) {
        objectGraph.createNewNode(clazz);
    }


    /**
     * Pridani sluzby rozhrani a implementaci do objektoveho grafu.
     * Klicem je rozhrani.
     *
     * @param clazzInterface rozhrani
     * @param clazzImpl      implementace
     */
    public void addService(Class clazzInterface, Class clazzImpl) {
        objectGraph.createNewNodeWithImpl(clazzInterface, clazzImpl);
    }


    /**
     * Detekuje, zda se v danem objektovem grafu vyskytuje cyklus
     * v zavislostech v konstruktoru.
     *
     * @param clazz vstupni trida
     * @return true pokud detekuje cyklus
     */
    public boolean detectConstructorCycle(Class clazz) {
        // mapa informaci navic, potrebnych pro dany algoritmus
        Map<ServiceNode, Status> metaInfo = new HashMap<>();

        // osetreni zda je trida v objektovem grafu
        objectGraph.isNodePresent(clazz);

        ServiceNode initNode = objectGraph.getNode(clazz);

        boolean result = dfs(initNode, metaInfo);

        return result;
    }

    /**
     * Zjisti a vrati pocet sluzeb spravovanych DI kontejnerem
     *
     * @return pocet sluzeb
     */
    public int getServicesCount() {
        return objectGraph.getAllNodes().size();
    }

    /**
     * Privatni pomocna rekutzivni metoda pro hledani cyklu
     *
     * @param root     korenovy uzel
     * @param metaInfo mapa udrzujici stav uzlu
     * @return true pokud detekuje cyklus
     */
    private boolean dfs(ServiceNode root, Map<ServiceNode, Status> metaInfo) {
        if (root == null) {
            return false;
        }

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
