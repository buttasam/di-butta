package cvut.fit.di.graph.api;


import cvut.fit.di.builder.injector.ConstructorInjector;
import cvut.fit.di.container.DIContainer;
import cvut.fit.di.container.constructor.cycle.CycleA;
import cvut.fit.di.graph.ObjectGraphAPI;
import org.junit.Test;

public class DetectCycleTest {


    @Test
    public void testDetectCycle() {
        DIContainer container = new DIContainer(new ConstructorInjector());
        container.initSubgraph(CycleA.class);

        ObjectGraphAPI api = new ObjectGraphAPI();

        api.detectCycle(CycleA.class);
    }


}
