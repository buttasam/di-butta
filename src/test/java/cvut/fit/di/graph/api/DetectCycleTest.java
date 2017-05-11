package cvut.fit.di.graph.api;


import cvut.fit.di.builder.injector.NotCycleConstructorInjector;
import cvut.fit.di.container.DIContainer;
import cvut.fit.di.graph.ObjectGraphAPI;
import cvut.fit.di.graph.ObjectGraphFactory;
import org.junit.Test;

public class DetectCycleTest {


    @Test
    public void testDetectCycle() {

        NotCycleConstructorInjector injector = new NotCycleConstructorInjector();
        DIContainer container = new DIContainer(injector);

        injector.initSubgraphByIntrospection(CycleA.class);

        ObjectGraphAPI api = new ObjectGraphAPI(ObjectGraphFactory.getObjectGraph());

        api.detectConstructorCycle(CycleA.class);
    }



}
