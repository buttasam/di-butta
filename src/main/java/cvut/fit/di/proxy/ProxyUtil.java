package cvut.fit.di.proxy;


import java.lang.reflect.Proxy;

/**
 * Pomocna trida pro praci s Proxy tridou.
 *
 * @author Samuel Butta
 */
public class ProxyUtil {


    /**
     * Vyvori proxy pro zadane rozhrani.
     * Vracena proxy implementuje i specialni rozhrani Instance setter,
     * diky kteremu je mozne nastavit skutecny objekt.
     *
     * @param iface rozhrani vytvarene proxy
     * @param <T>   typovy parametr
     * @return proxy instance
     */
    @SuppressWarnings("unchecked")
    public static <T> T createProxy(Class<T> iface) {
        return (T) Proxy.newProxyInstance(
                iface.getClassLoader(),
                new Class<?>[]{iface, InstanceSetter.class}, // vraci objekt co implementuje obe rozhrani
                new ProxyInvocationHandler());
    }


    /**
     * Dane proxy instanci nastavi skutecny objekt.
     *
     * @param proxy  instance proxy
     * @param target skutecny objekt
     * @param <T>    typovy parametr
     * @return vrati proxy s nastavenym skutecnym objektem
     */
    public static <T> T setInstance(T proxy, T target) {
        InstanceSetter<T> tmp = (InstanceSetter<T>) proxy;
        tmp.setInstance(target);

        return proxy;
    }

}
