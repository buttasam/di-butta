package cvut.fit.di.container.constructor.notcircular.impl;

import cvut.fit.di.builder.injector.NotCycleConstructorInjector;
import cvut.fit.di.builder.injector.SetterInjector;
import cvut.fit.di.container.DIContainer;
import org.junit.Assert;
import org.junit.Test;


/**
 * Test sluzby, ktere implementuji rozhrani.
 */
public class DIContainerConstructorImplTest {


    @Test
    public void testImplWithInterface() {
        DIContainer container = new DIContainer(new NotCycleConstructorInjector());
        A a = container.getInstance(A.class);
        B b = container.getInstance(B.class);

        A a1 = container.getInstance(A.class);

        DIContainer container2 = new DIContainer(new NotCycleConstructorInjector());
        A aCon2 = container2.getInstance(A.class);

        Assert.assertEquals(a, a1);
        Assert.assertNotEquals(a, aCon2);

        Assert.assertEquals("test", aCon2.getB().test());
        Assert.assertEquals("test", a.getB().test());
    }

}