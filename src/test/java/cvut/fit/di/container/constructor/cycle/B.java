package cvut.fit.di.container.constructor.cycle;

import javax.inject.Inject;
import javax.inject.Singleton;


@Singleton
public class B {

    private A a;

    @Inject
    public B(A a) {
        this.a = a;
    }

    public A getA() {
        return a;
    }
}
