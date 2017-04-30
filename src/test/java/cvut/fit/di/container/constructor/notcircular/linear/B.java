package cvut.fit.di.container.constructor.notcircular.linear;

import javax.inject.Inject;
import javax.inject.Singleton;


@Singleton
public class B {

    private C c;

    @Inject
    public B(C c) {
        this.c = c;
    }

    public C getC() {
        return c;
    }
}
