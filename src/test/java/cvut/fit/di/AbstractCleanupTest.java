package cvut.fit.di;

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
    }

}
