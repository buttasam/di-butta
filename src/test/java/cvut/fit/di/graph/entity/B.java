package cvut.fit.di.graph.entity;

import javax.inject.Inject;

/**
 * Created by samik on 14.3.17.
 */
public class B {

    private C c;

    @Inject
    public void setC(C c) {
        this.c = c;
    }

}
