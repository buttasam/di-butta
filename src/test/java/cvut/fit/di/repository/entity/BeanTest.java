package cvut.fit.di.repository.entity;

import cvut.fit.di.testEntity.scope.PrototypeEntity;
import cvut.fit.di.testEntity.scope.SingletonEntity;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Samuel Butta
 */
public class BeanTest {

    @Test
    public void testPrototypeScope() {
        Bean bean = new Bean(PrototypeEntity.class);

        Assert.assertEquals(BeanScope.PROTOTYPE, bean.getBeanScope());
    }

    @Test
    public void testSingletonScope() {
        Bean bean = new Bean(SingletonEntity.class);

        Assert.assertEquals(BeanScope.SINGLETON, bean.getBeanScope());
    }

}
