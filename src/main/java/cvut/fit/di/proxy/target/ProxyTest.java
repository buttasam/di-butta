package cvut.fit.di.proxy.target;

import cvut.fit.di.proxy.target.service.ServiceA;
import cvut.fit.di.proxy.target.service.ServiceAImpl;

/**
 * Created by samik on 27.4.17.
 */
public class ProxyTest {

    public static void main(String[] args) {

        ServiceA target = new ServiceAImpl();

        ServiceA proxy = ProxyUtil.createProxy(target, ServiceA.class);
        proxy.test();
    }

}
