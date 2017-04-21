package cvut.fit.di.util;

import cvut.fit.di.exception.service.ServiceAndImplementationDoesNotMatchException;
import cvut.fit.di.exception.service.ServiceImplementsMoreInterfacesException;
import cvut.fit.di.exception.service.ServiceMustImplementInterfaceException;
import cvut.fit.di.util.service.*;
import org.junit.Assert;
import org.junit.Test;


public class ValidatorTest {


    @Test
    public void testClassHasOneValidInterface() {
        boolean result = Validator.isClazzImplementationOfInterface(InterfaceA.class, ServiceWithInterfaceAImpl.class);

        Assert.assertTrue(result);
    }

    @Test(expected = ServiceImplementsMoreInterfacesException.class)
    public void testClassWithTwoInterfaces() {
        Validator.isClazzImplementationOfInterface(InterfaceA.class, ServiceWithTwoImpl.class);
    }

    @Test(expected = ServiceMustImplementInterfaceException.class)
    public void testClassWithoutInterfaces() {
        Validator.isClazzImplementationOfInterface(InterfaceA.class, ServiceWithoutImpl.class);
    }

    @Test(expected = ServiceAndImplementationDoesNotMatchException.class)
    public void testClassWiAndInterfaceDoesNotMatch() {
        Validator.isClazzImplementationOfInterface(InterfaceB.class, ServiceWithInterfaceAImpl.class);
    }


}