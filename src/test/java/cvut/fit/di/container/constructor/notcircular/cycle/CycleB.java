package cvut.fit.di.container.constructor.notcircular.cycle;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author Samuel Butta
 */
@Singleton
class CycleB {

    private CycleA cycleA;

    @Inject
    public CycleB(CycleA cycleA) {
        this.cycleA = cycleA;
    }

    public CycleA getCycleA() {
        return cycleA;
    }
}
