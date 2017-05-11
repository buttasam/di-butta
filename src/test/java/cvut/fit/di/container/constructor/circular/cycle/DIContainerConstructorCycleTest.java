package cvut.fit.di.container.constructor.circular.cycle;

import cvut.fit.di.AbstractCleanupTest;
import cvut.fit.di.builder.injector.CycleConstructorInjector;
import cvut.fit.di.container.DIContainer;
import cvut.fit.di.exception.service.AllServiceMustImplementInterfaceException;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

/**
 * @author Samuel Butta
 */
public class DIContainerConstructorCycleTest extends AbstractCleanupTest {

    @Test
    public void testCycleConstructorDI() throws InvocationTargetException, IllegalAccessException {

        DIContainer container = new DIContainer(new CycleConstructorInjector());

        CycleA a = container.getInstance(CycleA.class);
        CycleA a2 = container.getInstance(CycleA.class);

        // porovnani singletonu
        Assert.assertEquals(a.hashCode(), a2.hashCode());


        CycleB b = container.getInstance(CycleB.class);
        CycleB b2 = container.getInstance(CycleB.class);

        // porovnani prototype - porovnani pres hashCode kvuli
        Assert.assertNotEquals(b.hashCode(), b2.hashCode());

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