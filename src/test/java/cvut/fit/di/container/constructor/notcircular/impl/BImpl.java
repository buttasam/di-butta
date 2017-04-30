package cvut.fit.di.container.constructor.notcircular.impl;

import javax.inject.Singleton;

/**
 * @author Samuel Butta
 */
@Singleton
public class BImpl implements B {

    public BImpl() {

    }

    @Override
    public String test() {
        return "test";
    }

}
