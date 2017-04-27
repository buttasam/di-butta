package cvut.fit.di.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;


public class ProxyInvocationHandler implements java.lang.reflect.InvocationHandler {

    private final Object target;

    public ProxyInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("calling proxy method");
        return method.invoke(target, args);
    }
}