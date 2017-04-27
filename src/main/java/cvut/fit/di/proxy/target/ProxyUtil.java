package cvut.fit.di.proxy.target;

import java.lang.reflect.Proxy;


public class ProxyUtil {


    @SuppressWarnings("unchecked")
    public static <T> T createProxy(T target, Class<T> iface) {
        return (T) Proxy.newProxyInstance(
                iface.getClassLoader(),
                new Class<?>[] { iface },
                new ProxyInvocationHandler(target));
    }

}
