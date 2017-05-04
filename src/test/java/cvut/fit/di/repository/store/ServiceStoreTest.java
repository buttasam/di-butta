package cvut.fit.di.repository.store;

import cvut.fit.di.exception.ServiceAlreadyExistsException;
import cvut.fit.di.testEntity.managed.ManagedOne;
import cvut.fit.di.testEntity.managed.withInterface.Mailer;
import cvut.fit.di.testEntity.managed.withInterface.SmsMailer;
import cvut.fit.di.testEntity.scope.PrototypeEntity;
import cvut.fit.di.testEntity.scope.SingletonEntity;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Samuel Butta
 */
public class ServiceStoreTest {


    @Test(expected = ServiceAlreadyExistsException.class)
    public void testServicesAdding() {
        ServiceStore serviceStore = new ServiceStore();
        serviceStore.addService(ManagedOne.class);
        serviceStore.addService(ManagedOne.class);

        ManagedOne managedOne = (ManagedOne) serviceStore.getInstance(ManagedOne.class);

        Assert.assertEquals(1, serviceStore.managedServicesCount());
    }


    @Test
    public void testServicesWithImpAdding() {
        ServiceStore serviceStore = new ServiceStore();
        serviceStore.addService(Mailer.class, SmsMailer.class);

        Assert.assertEquals(1, serviceStore.managedServicesCount());
    }

    @Test
    public void testSingletonInstance() {
        ServiceStore serviceStore = new ServiceStore();
        serviceStore.addService(PrototypeEntity.class);

        PrototypeEntity prototype1 = (PrototypeEntity) serviceStore.getInstance(PrototypeEntity.class);
        PrototypeEntity prototype2 = (PrototypeEntity) serviceStore.getInstance(PrototypeEntity.class);

        Assert.assertNotEquals(prototype1, prototype2);
    }

    @Test
    public void testPrototypeInstance() {
        ServiceStore serviceStore = new ServiceStore();
        serviceStore.addService(SingletonEntity.class);

        SingletonEntity singleton1 = (SingletonEntity) serviceStore.getInstance(SingletonEntity.class);
        SingletonEntity singleton2 = (SingletonEntity) serviceStore.getInstance(SingletonEntity.class);

        Assert.assertEquals(singleton1, singleton2);
    }

}
