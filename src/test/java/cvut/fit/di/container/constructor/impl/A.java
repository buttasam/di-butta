package cvut.fit.di.container.constructor.impl;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author Samuel Butta
 */
@Singleton
public class A {

    private B b;

    @Inject
    public A(B b) {
        this.b = b;
    }

    public B getB() {
        return b;
    }
}
