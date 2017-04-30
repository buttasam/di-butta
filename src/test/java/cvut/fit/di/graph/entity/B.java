package cvut.fit.di.graph.entity;

import cvut.fit.di.anotation.scope.Prototype;

import javax.inject.Inject;


@Prototype
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
