package cvut.fit.di.container.constructor.cycle;

import cvut.fit.di.builder.injector.NotCycleConstructorInjector;
import cvut.fit.di.container.DIContainer;
import cvut.fit.di.exception.CircularDependencyFoundException;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;


public class DIContainerConstructorCycleTest {

    @Test
    public void testObjectGraphAPIDetectCycleConstructorDI() throws InvocationTargetException, IllegalAccessException {

        DIContainer container = new DIContainer(new NotCycleConstructorInjector());

        container.initSubgraph(CycleA.class);

        Assert.assertTrue(container.getAPI().detectCycle(CycleA.class));
        Assert.assertTrue(container.getAPI().detectCycle(CycleB.class));
    }


    @Test
    public void testObjectGraphAPIDetectNotCycleConstructorDI() throws InvocationTargetException, IllegalAccessException {
        DIContainer container = new DIContainer(new NotCycleConstructorInjector());

        container.initSubgraph(NoCycleA.class);

        Assert.assertFalse(container.getAPI().detectCycle(NoCycleA.class));
    }

    @Test(expected = CircularDependencyFoundException.class)
    public void testDetectCycleConstructorDI() throws InvocationTargetException, IllegalAccessException {
        DIContainer container = new DIContainer(new NotCycleConstructorInjector());

        CycleA cycleA = container.getInstance(CycleA.class);
    }


}