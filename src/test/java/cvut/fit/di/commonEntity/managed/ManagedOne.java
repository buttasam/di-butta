package cvut.fit.di.commonEntity.managed;

import cvut.fit.di.anotation.scope.Prototype;

/**
 * Created by samik on 28.11.16.
 */

@Prototype
public class ManagedOne {

    public String test() {
        return ManagedOne.class.toString();
    }

}
