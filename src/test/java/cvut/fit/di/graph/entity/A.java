package cvut.fit.di.graph.entity;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class A {

    @Inject
    private B b;

    private D d;

    @Inject
    public A(D d) {
        this.d = d;
    }

    public void setB(B b) {
        this.b = b;
    }
}
