package cvut.fit.di.proxy;


import java.lang.reflect.Proxy;


public class ProxyUtil {


    @SuppressWarnings("unchecked")
    public static <T> T createProxy(Class<T> iface) {
        return (T) Proxy.newProxyInstance(
                iface.getClassLoader(),
                new Class<?>[] { iface, InstanceSetter.class }, // vraci objekt co implementuje obe rozhrani
                new ProxyInvocationHandler());
    }


    public static <T> T setInstance(T proxy, T target) {
        InstanceSetter<T> tmp = (InstanceSetter<T>) proxy;
        tmp.setInstance(target);

        return proxy;
    }

}
