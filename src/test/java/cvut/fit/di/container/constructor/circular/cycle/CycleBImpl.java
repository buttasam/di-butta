package cvut.fit.di.container.constructor.circular.cycle;

import cvut.fit.di.anotation.scope.Prototype;

import javax.inject.Inject;


@Prototype
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
