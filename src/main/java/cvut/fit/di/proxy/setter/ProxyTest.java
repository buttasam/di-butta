package cvut.fit.di.proxy.setter;

import cvut.fit.di.proxy.setter.service.Instancer;
import cvut.fit.di.proxy.setter.service.ServiceA;
import cvut.fit.di.proxy.setter.service.ServiceAImpl;

/**
 * Created by samik on 27.4.17.
 */
public class ProxyTest {

    public static void main(String[] args) {


        ServiceA proxy = ProxyUtil.createProxy(ServiceA.class);

        ServiceA target = new ServiceAImpl();


        Instancer<ServiceA> tmp = (Instancer<ServiceA>) proxy;
        tmp.setInstance(target);

        proxy.test();
    }

}
