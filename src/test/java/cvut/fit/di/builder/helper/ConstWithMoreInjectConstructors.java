package cvut.fit.di.builder.helper;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author Samuel Butta
 */
@Singleton
public class ConstWithMoreInjectConstructors {

    public ServiceA a;
    public ServiceB b;

    @Inject
    public ConstWithMoreInjectConstructors(ServiceA a) {
        this.a = a;
    }

    @Inject
    public ConstWithMoreInjectConstructors(ServiceA a, ServiceB b) {
        this.a = a;
        this.b = b;
    }
}
