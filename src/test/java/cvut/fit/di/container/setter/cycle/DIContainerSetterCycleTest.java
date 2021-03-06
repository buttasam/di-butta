package cvut.fit.di.container.setter.cycle;

import cvut.fit.di.builder.injector.SetterInjector;
import cvut.fit.di.container.DIContainer;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;


public class DIContainerSetterCycleTest {

    @Test
    public void testCycleSetterDI() throws InvocationTargetException, IllegalAccessException {

        DIContainer container = new DIContainer(new SetterInjector());

        A a = (A) container.getInstance(A.class);
        B b = a.getB();

        Assert.assertNotNull(a);
        Assert.assertNotNull(b);

        Assert.assertEquals((B) container.getInstance(B.class), b);
    }



}