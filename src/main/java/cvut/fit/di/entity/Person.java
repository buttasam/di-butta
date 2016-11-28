package cvut.fit.di.entity;

import cvut.fit.di.reflextion.Wired;

/**
 * Created by samik on 28.11.16.
 */
public class Person {

    @Wired
    private Car car;

    private String name;

}
