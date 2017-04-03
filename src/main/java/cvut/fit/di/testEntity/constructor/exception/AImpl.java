package cvut.fit.di.testEntity.constructor.exception;

import cvut.fit.di.testEntity.constructor.AInterface;

import javax.inject.Singleton;

/**
 * @author Samuel Butta
 */
@Singleton
public class AImpl implements AInterface {

    @Override
    public void print() {
        System.out.println("AImpl");
    }
}
