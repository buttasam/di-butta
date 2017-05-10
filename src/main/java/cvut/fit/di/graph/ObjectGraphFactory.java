package cvut.fit.di.graph;

/**
 * Singleton reprezentace objektoveho grafu.
 */
public class ObjectGraphFactory {

    private static ObjectGraph instance;


    public static ObjectGraph getObjectGraph() {
        if(instance == null) {
            instance = new ObjectGraph();
        }

        return instance;
    }


    public static void reinit() {
        instance = new ObjectGraph();
    }
}
