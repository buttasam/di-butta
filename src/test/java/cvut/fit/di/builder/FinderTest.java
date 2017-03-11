package cvut.fit.di.builder;

import cvut.fit.di.exception.AmbiguousImplementationException;
import cvut.fit.di.exception.MissingImplementationException;
import cvut.fit.di.testEntity.Car;
import cvut.fit.di.testEntity.managed.injected.CarDao;
import cvut.fit.di.testEntity.managed.injected.UserService;
import cvut.fit.di.testEntity.managed.withInterface.Mailer;
import cvut.fit.di.testEntity.managed.withInterface.unique.UniqueMailMailer;
import cvut.fit.di.testEntity.managed.withInterface.unique.UniqueMailer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
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
    public void setServicesTest() {
        Set<Method> methods = finder.findInjectedSetters(UserService.class);

        Creator creator = new Creator();
        UserService userService = new UserService();

        methods.stream().forEach(method -> {
            Class<?>[] paramTypes = method.getParameterTypes();

            Class cls = null;
            try {
                cls = Class.forName("cvut.fit.di.testEntity.managed.injected.UserService");
                Object obj = cls.newInstance();


                for (Class paramType: paramTypes) {
                    System.out.println(method.getName());
                    method.invoke(userService, creator.createNewInstance(paramType));
                }

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

            System.out.println(userService);
            System.out.println(userService);



/*            for (Class paramType: paramTypes) {
                System.out.println(paramType.getName());
                try {
                    System.out.println(method.getName());

                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }*/

            System.out.println(method.getParameterCount());
        });

    }
}
