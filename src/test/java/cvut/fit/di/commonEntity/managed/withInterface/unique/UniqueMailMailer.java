package cvut.fit.di.commonEntity.managed.withInterface.unique;

import javax.inject.Singleton;

/**
 * @author Samuel Butta
 */

@Singleton
public class UniqueMailMailer implements UniqueMailer {

    @Override
    public String test() {
        return UniqueMailMailer.class.toString();
    }

}
