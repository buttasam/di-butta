package cvut.fit.di.container.constructor.notcircular.cycle;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author Samuel Butta
 */
@Singleton
class CycleA {

    private CycleB cycleB;

    @Inject
    public CycleA(CycleB cycleB) {
        this.cycleB = cycleB;
    }

    public CycleB getCycleB() {
        return cycleB;
    }
}
