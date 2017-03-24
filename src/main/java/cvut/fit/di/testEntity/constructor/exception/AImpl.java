package cvut.fit.di.testEntity.constructor.exception;

import cvut.fit.di.testEntity.constructor.AInterface;

/**
 * @author Samuel Butta
 */
public class AImpl implements AInterface {

    @Override
    public void print() {
        System.out.println("AImpl");
    }
}
