package cvut.fit.di.repository.store;

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
public class BeanStoreTest {


    @Test
    public void testBeansAdding() {
        BeanStore beanStore = new BeanStore();
        beanStore.addBean(ManagedOne.class);
        beanStore.addBean(ManagedOne.class);


        ManagedOne managedOne = (ManagedOne) beanStore.getInstance(ManagedOne.class);
        managedOne.print();

        Assert.assertEquals(1, beanStore.managedBeansCount());
    }


    @Test
    public void testBeansWithImpAdding() {
        BeanStore beanStore = new BeanStore();
        beanStore.addBean(Mailer.class, SmsMailer.class);

        Assert.assertEquals(1, beanStore.managedBeansCount());
    }

    @Test
    public void testSingletonInstance() {
        BeanStore beanStore = new BeanStore();
        beanStore.addBean(PrototypeEntity.class);

        PrototypeEntity prototype1 = (PrototypeEntity) beanStore.getInstance(PrototypeEntity.class);
        PrototypeEntity prototype2 = (PrototypeEntity) beanStore.getInstance(PrototypeEntity.class);

        Assert.assertNotEquals(prototype1, prototype2);
    }

    @Test
    public void testPrototypeInstance() {
        BeanStore beanStore = new BeanStore();
        beanStore.addBean(SingletonEntity.class);

        SingletonEntity singleton1 = (SingletonEntity) beanStore.getInstance(SingletonEntity.class);
        SingletonEntity singleton2 = (SingletonEntity) beanStore.getInstance(SingletonEntity.class);

        Assert.assertEquals(singleton1, singleton2);
    }

}
