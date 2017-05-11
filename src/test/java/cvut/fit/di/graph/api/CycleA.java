package cvut.fit.di.graph.api;

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
