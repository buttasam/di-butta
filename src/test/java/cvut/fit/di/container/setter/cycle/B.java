package cvut.fit.di.container.setter.cycle;

import javax.inject.Inject;
import javax.inject.Singleton;


@Singleton
public class B {

    private A a;

    @Inject
    public void setA(A a) {
        this.a = a;
    }

    public A getA() {
        return a;
    }
}
