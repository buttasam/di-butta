package cvut.fit.di.graph.entity;

import javax.inject.Inject;

/**
 * Created by samik on 14.3.17.
 */
public class C {


    private A a;


    @Inject
    public void setA(A a) {
        this.a = a;
    }
}
