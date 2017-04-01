package cvut.fit.di.container.setter.linear;

import cvut.fit.di.builder.injector.SetterInjector;
import cvut.fit.di.container.DIContainer;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by samik on 1.4.17.
 */
public class DIContainerSetterTest {

    @Test
    public void testLinearSetterDI() throws InvocationTargetException, IllegalAccessException {

        DIContainer container = new DIContainer(new SetterInjector());

        A a = (A) container.getInstance(A.class);

        a.getB();
    }



}