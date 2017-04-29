package cvut.fit.di.container.setter.impl;

import cvut.fit.di.container.DIContainer;
import org.junit.Assert;
import org.junit.Test;


/**
 * Test sluzby, ktere implementuji rozhrani.
 */
public class DIContainerSetterImplTest {


    @Test
    public void testImplWithInterface() {
        DIContainer container = new DIContainer();
        A a = container.getInstance(A.class);

        Assert.assertEquals("test", a.getB().test());
    }

}