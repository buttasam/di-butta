package cvut.fit.di.container.constructor.circular.cycle;

import javax.inject.Singleton;


@Singleton
public class CycleCImpl implements CycleC {

    @Override
    public String getImplName() {
        return CycleCImpl.class.getName();
    }
}
