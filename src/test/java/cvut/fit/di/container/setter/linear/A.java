package cvut.fit.di.container.setter.linear;

import javax.inject.Inject;
import javax.inject.Singleton;



public class A {

    private B b;

    @Inject
    public void setB(B b) {
        this.b = b;
    }

    public B getB() {
        return b;
    }
}
