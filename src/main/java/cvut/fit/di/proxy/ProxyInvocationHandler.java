package cvut.fit.di.proxy;

import cvut.fit.di.anotation.proxy.TargetInstanceSetter;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * Implementace proxy handleru.
 * Urzuje instanci skutecneho objektu.
 *
 * @author Samuel Butta
 */
public class ProxyInvocationHandler implements InvocationHandler {

    /**
     * Skutecny objekt, jehoz metody jsou volany proxy tridou.
     */
    private Object target;

    public ProxyInvocationHandler() {

    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        // porovnani metody
        if (method.isAnnotationPresent(TargetInstanceSetter.class)) {
            this.target = args[0];
            return null;
        } else {
            return method.invoke(target, args);
        }
    }
}