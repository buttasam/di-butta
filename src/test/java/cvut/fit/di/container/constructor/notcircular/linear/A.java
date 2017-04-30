package cvut.fit.di.container.constructor.notcircular.linear;

import javax.inject.Inject;
import javax.inject.Singleton;


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
