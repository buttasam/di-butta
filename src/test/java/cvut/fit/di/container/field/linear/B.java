package cvut.fit.di.container.field.linear;

import javax.inject.Inject;
import javax.inject.Singleton;


@Singleton
public class B {

    @Inject
    private C c;

    public C getC() {
        return c;
    }
}
