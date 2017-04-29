package cvut.fit.di.container.field.impl;

import javax.inject.Singleton;

/**
 * @author Samuel Butta
 */
@Singleton
public class BImpl implements B {

    @Override
    public String test() {
        return "test";
    }

}
