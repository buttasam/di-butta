package cvut.fit.di.graph.entity;

import javax.inject.Inject;

/**
 * Created by samik on 14.3.17.
 */
public class A {

    private B b;

    private C c;


    @Inject
    public void setB(B b) {
        this.b = b;
    }

    @Inject
    public void setC(C c) {
        this.c = c;
    }
}
