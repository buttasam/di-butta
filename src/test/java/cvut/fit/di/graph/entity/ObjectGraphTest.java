package cvut.fit.di.graph.entity;

import cvut.fit.di.graph.ClassNode;
import cvut.fit.di.graph.ObjectGraph;
import org.junit.Test;

/**
 * Created by samik on 14.3.17.
 */
public class ObjectGraphTest {


    @Test
    public void testObjectGraphInit() {

        ObjectGraph graph = new ObjectGraph();
        ClassNode node = graph.initNode(A.class);

        System.out.println(node.getClass().toString());
    }

}
