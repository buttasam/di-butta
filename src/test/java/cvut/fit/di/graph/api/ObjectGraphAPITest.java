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

        Assert.assertEquals(4, api.getServicesCount());
    }


}
