package cvut.fit.di.proxy;

import org.junit.Assert;
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

        Assert.assertEquals(ServiceAImpl.class.toString(), proxy.test());
    }

}
