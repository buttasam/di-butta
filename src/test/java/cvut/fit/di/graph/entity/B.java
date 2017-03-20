package cvut.fit.di.graph.entity;

import javax.inject.Inject;

/**
 * @author Samuel Butta
 */
public class B {

    private C c;

    public C getC() {
        return c;
    }

    @Inject
    public void setC(C c) {
        this.c = c;
    }
}
