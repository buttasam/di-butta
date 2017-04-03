package cvut.fit.di.container.constructor.cycle;

import javax.inject.Inject;
import javax.inject.Singleton;


@Singleton
public class CycleB {

    private CycleA cycleA;

    @Inject
    public CycleB(CycleA cycleA) {
        this.cycleA = cycleA;
    }

    public CycleA getCycleA() {
        return cycleA;
    }
}
