package cvut.fit.di.repository.store;

import cvut.fit.di.testEntity.managed.ManagedOne;
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

        Assert.assertEquals(1, beanStore.managedBeansCount());
    }


}
