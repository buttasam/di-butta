package cvut.fit.di;

import cvut.fit.di.graph.ObjectGraphFactory;
import cvut.fit.di.repository.store.ServiceStoreFactory;
import org.junit.Before;

/**
 * @author Samuel Butta
 */
public class AbstractCleanupTest {

    @Before
    public void setUp() {
        ObjectGraphFactory.reinit();
        ServiceStoreFactory.reinit();
    }

}
