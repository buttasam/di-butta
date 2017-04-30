package cvut.fit.di.container.constructor.circular.cycle;

import javax.inject.Inject;
import javax.inject.Singleton;


@Singleton
public class CycleBImpl implements CycleB {

    private CycleA cycleA;

    @Inject
    public CycleBImpl(CycleA cycleA) {
        this.cycleA = cycleA;
    }


    @Override
    public String getImplName() {
        return CycleBImpl.class.getName();
    }
}
