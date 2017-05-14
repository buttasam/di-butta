package cvut.fit.di.container.constructor.circular.cycle;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author Samuel Butta
 */
@Singleton
public class CycleAImpl implements CycleA {

    private CycleB cycleB;
    private CycleC cycleC;

    @Inject
    public CycleAImpl(CycleB cycleB, CycleC cycleC) {
        this.cycleB = cycleB;
        this.cycleC = cycleC;
    }


    @Override
    public CycleB getB() {
        return cycleB;
    }

    @Override
    public CycleC getC() {
        return cycleC;
    }

    @Override
    public String test() {
        return CycleAImpl.class.toString();
    }
}
