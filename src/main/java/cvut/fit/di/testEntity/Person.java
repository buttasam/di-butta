package cvut.fit.di.testEntity;

import cvut.fit.di.anotation.Managed;
import cvut.fit.di.reflextion.Wired;

/**
 * Created by samik on 28.11.16.
 */
@Managed
public class Person {

    @Wired
    private Car car;

    private String name;

}
