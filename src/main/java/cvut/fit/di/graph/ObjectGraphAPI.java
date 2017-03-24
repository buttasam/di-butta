package cvut.fit.di.graph;


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


}
