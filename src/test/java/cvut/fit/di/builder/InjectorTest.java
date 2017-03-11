package cvut.fit.di.builder;

import cvut.fit.di.exception.AmbiguousImplementationException;
import cvut.fit.di.exception.MissingImplementationException;
import cvut.fit.di.testEntity.managed.injected.UserService;
import cvut.fit.di.testEntity.managed.withInterface.Mailer;
import cvut.fit.di.testEntity.managed.withInterface.unique.UniqueMailMailer;
import cvut.fit.di.testEntity.managed.withInterface.unique.UniqueMailer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.inject.Inject;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;

/**
 * @author Samuel Butta
 */
public class InjectorTest {


    private Injector injector;

    @Before
    public void startUp() {
        injector = new Injector();
    }


    @Test
    public void testInjectSetterDependencies() {
        Finder finder = new Finder();
        Set<Method> setters = finder.findInjectedSetters(UserService.class);

        UserService userService = new UserService();

        injector.injectSetterDependencies(userService, setters);

        Assert.assertNotNull(userService.getCarDao());
        Assert.assertNotNull(userService.getUserDao());
    }
}
