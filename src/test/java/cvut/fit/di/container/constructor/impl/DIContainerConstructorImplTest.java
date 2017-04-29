package cvut.fit.di.container.constructor.impl;

import cvut.fit.di.builder.injector.FieldInjector;
import cvut.fit.di.builder.injector.NotCycleConstructorInjector;
import cvut.fit.di.container.DIContainer;
import org.junit.Assert;
import org.junit.Test;


/**
 * Test sluzby, ktere implementuji rozhrani.
 */
public class DIContainerConstructorImplTest {


    // FIXME
    //@Test
    public void testImplWithInterface() {
        DIContainer container = new DIContainer(new NotCycleConstructorInjector());
        A a = container.getInstance(A.class);

        Assert.assertEquals("test", a.getB().test());
    }

}