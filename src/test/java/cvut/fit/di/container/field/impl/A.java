package cvut.fit.di.container.field.impl;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author Samuel Butta
 */
@Singleton
public class A {

    @Inject
    private B b;

    public B getB() {
        return b;
    }
}
