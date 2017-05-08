package cvut.fit.di.container.constructor.circular.cycle;

import cvut.fit.di.builder.injector.CycleConstructorInjector;
import cvut.fit.di.container.DIContainer;
import cvut.fit.di.exception.service.AllServiceMustImplementInterfaceException;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;


public class DIContainerConstructorCycleTest {

    @Test
    public void testCycleConstructorDI() throws InvocationTargetException, IllegalAccessException {

        DIContainer container = new DIContainer(new CycleConstructorInjector());

        CycleA a = container.getInstance(CycleA.class);
        System.out.println(container.getAPI().servicesCount());

        a.test();

        Assert.assertNotNull(a.getB().getA());

        Assert.assertEquals(CycleBImpl.class.getName(), a.getB().getImplName());
        Assert.assertEquals(CycleCImpl.class.getName(), a.getC().getImplName());
    }


    @Test(expected = AllServiceMustImplementInterfaceException.class)
    public void testCycleConstructorDIMissingInterfaces() {
        DIContainer container = new DIContainer(new CycleConstructorInjector());

        container.getInstance(MissingInterface.class);
    }

}