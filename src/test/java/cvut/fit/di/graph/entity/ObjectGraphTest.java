package cvut.fit.di.graph.entity;

import cvut.fit.di.exception.service.UndefinedScopeOfServiceClassException;
import cvut.fit.di.graph.ObjectGraph;
import org.junit.Assert;
import org.junit.Test;


public class ObjectGraphTest {


    @Test
    public void test() {
        ObjectGraph objectGraph = new ObjectGraph();

        objectGraph.initSubgraphByNode(A.class);

        Assert.assertNotNull(objectGraph.getNode(A.class));
        Assert.assertNotNull(objectGraph.getNode(B.class));
        Assert.assertNotNull(objectGraph.getNode(C.class));
        Assert.assertNotNull(objectGraph.getNode(D.class));
    }


    @Test(expected = UndefinedScopeOfServiceClassException.class)
    public void testUndefinedScopeOfServiceClassException() {
        ObjectGraph objectGraph = new ObjectGraph();

        objectGraph.initSubgraphByNode(UndefinedScope.class);
    }


}
