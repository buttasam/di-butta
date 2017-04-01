package cvut.fit.di.container.field.cycle;

import javax.inject.Inject;
import javax.inject.Singleton;


@Singleton
public class A {

    @Inject
    private B b;

    public B getB() {
        return b;
    }
}
