package cvut.fit.di.graph.api;

import cvut.fit.di.graph.ObjectGraph;
import cvut.fit.di.graph.ObjectGraphAPI;
import cvut.fit.di.graph.ServiceNode;
import cvut.fit.di.graph.entity.A;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Samuel Butta
 */
public class ObjectGraphAPITest {


    @Test
    public void testServiceCount() {

        ObjectGraph graph = new ObjectGraph();
        ServiceNode node = graph.initSubgraphByNode(A.class);

        ObjectGraphAPI api = new ObjectGraphAPI(graph);

        Assert.assertEquals(4, api.servicesCount());
    }

    @Test
    public void testInterfacesCount() {
        ObjectGraph graph = new ObjectGraph();
        ServiceNode node = graph.initSubgraphByNode(A.class);

        ObjectGraphAPI api = new ObjectGraphAPI(graph);

        Assert.assertEquals(1, api.interfacesCount());
    }

    @Test
    public void testServicesWithoutInterfaceCount() {
        ObjectGraph graph = new ObjectGraph();
        ServiceNode node = graph.initSubgraphByNode(A.class);

        ObjectGraphAPI api = new ObjectGraphAPI(graph);

        Assert.assertEquals(3, api.servicesWithoutInterfaceCount());
    }

    @Test
    public void testPrototypesCount() {
        ObjectGraph graph = new ObjectGraph();
        ServiceNode node = graph.initSubgraphByNode(A.class);

        ObjectGraphAPI api = new ObjectGraphAPI(graph);

        Assert.assertEquals(2, api.prototypesCount());
    }

    @Test
    public void testSingletonsCount() {
        ObjectGraph graph = new ObjectGraph();
        ServiceNode node = graph.initSubgraphByNode(A.class);

        ObjectGraphAPI api = new ObjectGraphAPI(graph);

        Assert.assertEquals(2, api.singletonsCount());
    }


    @Test
    public void testAllServicesHasInterface() {
        ObjectGraph graph = new ObjectGraph();
        ServiceNode node = graph.initSubgraphByNode(A.class);

        ObjectGraphAPI api = new ObjectGraphAPI(graph);

        Assert.assertFalse(api.allServicesHasInterface());
    }

}
