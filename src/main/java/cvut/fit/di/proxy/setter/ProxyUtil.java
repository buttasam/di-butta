package cvut.fit.di.proxy.setter;

import cvut.fit.di.proxy.setter.service.Instancer;

import java.lang.reflect.Proxy;


public class ProxyUtil {


    @SuppressWarnings("unchecked")
    public static <T> T createProxy(Class<T> iface) {
        return (T) Proxy.newProxyInstance(
                iface.getClassLoader(),
                new Class<?>[] { iface, Instancer.class }, // vraci objekt co implementuje obe rozhrani
                new ProxyInvocationHandler());
    }

}
