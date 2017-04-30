package cvut.fit.di.container.constructor.circular.cycle;

import cvut.fit.di.builder.injector.CycleConstructorInjector;
import cvut.fit.di.container.DIContainer;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;


public class DIContainerConstructorCycleTest {

    @Test
    public void testObjectGraphAPIDetectCycleConstructorDI() throws InvocationTargetException, IllegalAccessException {

        DIContainer container = new DIContainer(new CycleConstructorInjector());

        CycleA a = container.getInstance(CycleA.class);

        Assert.assertEquals(CycleBImpl.class.getName(), a.getB().getImplName());
        Assert.assertEquals(CycleCImpl.class.getName(), a.getC().getImplName());
    }

}