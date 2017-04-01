package cvut.fit.di.container.field.linear;

import cvut.fit.di.builder.injector.FieldInjector;
import cvut.fit.di.builder.injector.SetterInjector;
import cvut.fit.di.container.DIContainer;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;


public class DIContainerFieldTest {

    @Test
    public void testLinearSetterDI() throws InvocationTargetException, IllegalAccessException {

        DIContainer container = new DIContainer(new FieldInjector());

        A a = (A) container.getInstance(A.class);

        Assert.assertNotNull(a);
        Assert.assertNotNull(a.getB());
        Assert.assertNotNull(a.getB().getC());
    }



}