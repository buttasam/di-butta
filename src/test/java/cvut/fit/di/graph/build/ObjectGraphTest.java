package cvut.fit.di.graph.build;

import cvut.fit.di.graph.ObjectGraph;
import cvut.fit.di.graph.ServiceNode;
import cvut.fit.di.graph.entity.A;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 */
public class ObjectGraphTest {


    @Test
    public void testObjectGraphInit() {

        ObjectGraph graph = new ObjectGraph();
        ServiceNode node = graph.initSubgraphByNode(A.class);

        Assert.assertEquals(1, node.constructorChildrenSize());
        Assert.assertEquals(0, node.setterChildrenSize());
        Assert.assertEquals(1, node.fieldChildrenSize());

    }

}
