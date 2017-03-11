package cvut.fit.di.builder;

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

}
