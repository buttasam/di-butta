package cvut.fit.di.container.constructor.cycle;

import cvut.fit.di.builder.injector.ConstructorInjector;
import cvut.fit.di.container.DIContainer;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;


public class DIContainerConstructorCycleTest {

    @Test
    public void testDetectCycleConstructorDI() throws InvocationTargetException, IllegalAccessException {

        DIContainer container = new DIContainer(new ConstructorInjector());

        container.initSubgraph(CycleA.class);

        Assert.assertTrue(container.getAPI().detectCycle(CycleA.class));
        Assert.assertTrue(container.getAPI().detectCycle(CycleB.class));
    }


    @Test
    public void testDetectNotCycleConstructorDI() throws InvocationTargetException, IllegalAccessException {
        DIContainer container = new DIContainer(new ConstructorInjector());

        container.initSubgraph(NoCycleA.class);

        Assert.assertFalse(container.getAPI().detectCycle(NoCycleA.class));
    }



}