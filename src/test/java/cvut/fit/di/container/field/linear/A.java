package cvut.fit.di.container.field.linear;

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
