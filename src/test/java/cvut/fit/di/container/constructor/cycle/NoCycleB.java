package cvut.fit.di.container.constructor.cycle;

import javax.inject.Inject;
import javax.inject.Singleton;


@Singleton
public class NoCycleB {

    private NoCycleC cycleC;

    @Inject
    public NoCycleB(NoCycleC cycleC) {
        this.cycleC = cycleC;
    }

    public NoCycleC getCycleC() {
        return cycleC;
    }
}
