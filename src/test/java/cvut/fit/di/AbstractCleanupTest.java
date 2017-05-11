package cvut.fit.di;

import cvut.fit.di.graph.ObjectGraphFactory;
import cvut.fit.di.repository.store.ServiceStoreFactory;
import org.junit.Before;

/**
 * Abstraktni predek, ktery pred spustenim kazdeho testu
 * znovu inicalizuje objketovy graf a uloziste sluzeb.
 *
 * @author Samuel Butta
 */
public abstract class AbstractCleanupTest {

    @Before
    public void setUp() {
        ObjectGraphFactory.reinit();
        ServiceStoreFactory.reinit();
    }

}
