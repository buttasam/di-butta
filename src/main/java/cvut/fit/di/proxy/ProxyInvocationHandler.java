package cvut.fit.di.proxy;

import cvut.fit.di.anotation.proxy.TargetInstanceSetter;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 *
 * @author Samuel Butta
 */
public class ProxyInvocationHandler implements InvocationHandler {

    private Object target;

    public ProxyInvocationHandler() {

    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        // porovnani metody
        if(method.isAnnotationPresent(TargetInstanceSetter.class)) {
            Parameter param = method.getParameters()[0];
            System.out.println(param.getName());
            this.target = args[0];
            return null;
        } else {
            System.out.println("calling proxy method");
            return method.invoke(target, args);
        }
    }
}