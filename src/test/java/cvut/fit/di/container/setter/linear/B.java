package cvut.fit.di.container.setter.linear;

import javax.inject.Inject;
import javax.inject.Singleton;


@Singleton
public class B {

    private C c;

    @Inject
    public void setC(C c) {
        this.c = c;
    }

    public C getC() {
        return c;
    }
}
