package cvut.fit.di.container.constructor.cycle;

import javax.inject.Inject;
import javax.inject.Singleton;


@Singleton
public class CycleA {

    private CycleB cycleB;

    @Inject
    public CycleA(CycleB cycleB) {
        this.cycleB = cycleB;
    }

    public CycleB getCycleB() {
        return cycleB;
    }
}
