package cvut.fit.di.graph.entity;

import cvut.fit.di.exception.service.UndefinedScopeOfServiceClassException;
import cvut.fit.di.graph.ObjectGraph;
import cvut.fit.di.graph.ServiceNode;
import cvut.fit.di.repository.entity.ServiceScope;
import org.junit.Assert;
import org.junit.Test;


public class ObjectGraphTest {


    @Test
    public void test() {
        ObjectGraph objectGraph = new ObjectGraph();

        objectGraph.initSubgraphByNode(A.class);


        ServiceNode nodeA = objectGraph.getNode(A.class);
        Assert.assertNotNull(nodeA);
        Assert.assertEquals(ServiceScope.SINGLETON, nodeA.getServiceScope());

        ServiceNode nodeB = objectGraph.getNode(B.class);
        Assert.assertNotNull(nodeA);
        Assert.assertEquals(ServiceScope.PROTOTYPE, nodeB.getServiceScope());


        ServiceNode nodeC = objectGraph.getNode(C.class);
        Assert.assertNotNull(nodeA);
        Assert.assertEquals(ServiceScope.SINGLETON, nodeC.getServiceScope());


        ServiceNode nodeD = objectGraph.getNode(D.class);
        Assert.assertNotNull(nodeA);
        Assert.assertEquals(ServiceScope.PROTOTYPE, nodeD.getServiceScope());
    }


    @Test(expected = UndefinedScopeOfServiceClassException.class)
    public void testUndefinedScopeOfServiceClassException() {
        ObjectGraph objectGraph = new ObjectGraph();

        objectGraph.initSubgraphByNode(UndefinedScope.class);
    }


}
