package cvut.fit.di.builder;

import cvut.fit.di.exception.AmbiguousImplementationException;
import cvut.fit.di.exception.MissingImplementationException;
import cvut.fit.di.testEntity.managed.withInterface.Mailer;
import cvut.fit.di.testEntity.managed.withInterface.unique.UniqueMailMailer;
import cvut.fit.di.testEntity.managed.withInterface.unique.UniqueMailer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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

}
