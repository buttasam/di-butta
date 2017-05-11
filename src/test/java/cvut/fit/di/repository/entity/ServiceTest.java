package cvut.fit.di.repository.entity;

import cvut.fit.di.commonEntity.scope.PrototypeEntity;
import cvut.fit.di.commonEntity.scope.SingletonEntity;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Samuel Butta
 */
public class ServiceTest {

    @Test
    public void testPrototypeScope() {
        Service service = new Service(PrototypeEntity.class);

        Assert.assertEquals(ServiceScope.PROTOTYPE, service.getServiceScope());
    }

    @Test
    public void testSingletonScope() {
        Service service = new Service(SingletonEntity.class);

        Assert.assertEquals(ServiceScope.SINGLETON, service.getServiceScope());
    }

}
