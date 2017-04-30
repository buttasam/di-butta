package cvut.fit.di.container.constructor.notcircular.cycle;

import javax.inject.Inject;
import javax.inject.Singleton;


@Singleton
public class NoCycleA {

    private NoCycleB cycleB;

    @Inject
    public NoCycleA(NoCycleB cycleB) {
        this.cycleB = cycleB;
    }

    public NoCycleB getCycleB() {
        return cycleB;
    }
}
