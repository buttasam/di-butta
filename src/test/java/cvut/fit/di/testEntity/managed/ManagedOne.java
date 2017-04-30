package cvut.fit.di.testEntity.managed;

import cvut.fit.di.anotation.scope.Prototype;

/**
 * Created by samik on 28.11.16.
 */

@Prototype
public class ManagedOne {

    public void print() {
        System.out.println("Manage One");
    }

}
