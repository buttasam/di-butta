package cvut.fit.di.graph.entity;

import javax.inject.Inject;


public class UndefinedScope {

    @Inject
    private B b;

    private D d;

    @Inject
    public UndefinedScope(D d) {
        this.d = d;
    }

    public void setB(B b) {
        this.b = b;
    }

}
