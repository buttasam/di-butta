package cvut.fit.di.container.field.cycle;

import javax.inject.Inject;
import javax.inject.Singleton;


@Singleton
public class B {

    @Inject
    private A a;

    public A getA() {
        return a;
    }
}
