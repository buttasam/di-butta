package cvut.fit.di.builder.helper;

import cvut.fit.di.exception.AmbiguousConstructorException;
import cvut.fit.di.exception.AmbiguousImplementationException;
import cvut.fit.di.exception.MissingImplementationException;
import cvut.fit.di.testEntity.constructor.AConst;
import cvut.fit.di.testEntity.constructor.BConst;
import cvut.fit.di.testEntity.constructor.exception.ConstWithMoreInjectConstructors;
import cvut.fit.di.testEntity.managed.injected.UserService;
import cvut.fit.di.testEntity.managed.withInterface.Mailer;
import cvut.fit.di.testEntity.managed.withInterface.unique.UniqueMailMailer;
import cvut.fit.di.testEntity.managed.withInterface.unique.UniqueMailer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Set;

/**
 * @author Samuel Butta
 */
public class FinderTest {


    private static final String MANAGE_TEST_ENTITY_PACKAGE = "cvut.fit.di.testEntity.managed";
    private static final String MANAGE_TEST_ENTITY_SUBPACKAGE = "cvut.fit.di.testEntity.managed.subManaged";

    private Finder finder;

    @Before
    public void startUp() {
        finder = new Finder();
    }


    /**
     * Testuje onanotovane tridy ve vsech subpackagech.
     */
    @Test
    public void testManagedClasses() {
        Set<Class<?>> annotated = finder.findManagedBeans(MANAGE_TEST_ENTITY_PACKAGE);

        Assert.assertEquals(4, annotated.size());
    }

    /**
     * Testuje onanotovane tridy v balicku bez jinych balicku.
     */
    @Test
    public void testManagedClassesInSubpackage() {
        Set<Class<?>> annotated = finder.findManagedBeans(MANAGE_TEST_ENTITY_SUBPACKAGE);

        Assert.assertEquals(2, annotated.size());
    }

    @Test
    public void testFindImplementation() throws MissingImplementationException, AmbiguousImplementationException {
        Class uniqueImp = finder.findImplementation(UniqueMailer.class);

        Assert.assertEquals(UniqueMailMailer.class, uniqueImp);
    }

    @Test(expected = AmbiguousImplementationException.class)
    public void testAmbiguousImplementation() throws MissingImplementationException, AmbiguousImplementationException {
        finder.findImplementation(Mailer.class);
    }

    @Test
    public void testFindInjectedSetters() {
        Set<Method> methods = finder.findInjectedSetters(UserService.class);

        Assert.assertEquals(2, methods.size());
    }


    @Test
    public void testFindInjectedFields() {
        Set<Field> fields = finder.findInjectedFields(cvut.fit.di.testEntity.field.UserService.class);

        Assert.assertEquals(1, fields.size());

    }

    @Test
    public void testFindInjectedConstructor() throws AmbiguousConstructorException {
        Constructor constructor = finder.findInjectedConstructor(AConst.class);
        Assert.assertNotNull(constructor);
    }

    @Test
    public void testFindInjectedConstructorWithNull() throws AmbiguousConstructorException {
        Constructor constructor = finder.findInjectedConstructor(BConst.class);
        Assert.assertNull(constructor);
    }


    @Test(expected = AmbiguousConstructorException.class)
    public void testFindInjectedConstructorWithException() throws AmbiguousConstructorException {
        finder.findInjectedConstructor(ConstWithMoreInjectConstructors.class);
    }
}
