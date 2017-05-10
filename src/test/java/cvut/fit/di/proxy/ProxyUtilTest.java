package cvut.fit.di.proxy;

import cvut.fit.di.proxy.service.ServiceA;
import cvut.fit.di.proxy.service.ServiceAImpl;
import org.junit.Test;

/**
 * @author Samuel Butta
 */
public class ProxyUtilTest {


    @Test
    public void testCreateProxy() {
        ServiceA proxy = ProxyUtil.createProxy(ServiceA.class);

        ServiceA target = new ServiceAImpl();
        proxy = ProxyUtil.setInstance(proxy, target);

        proxy.test();
    }

}