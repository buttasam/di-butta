package cvut.fit.di.builder.helper;

import cvut.fit.di.testEntity.managed.injected.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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
