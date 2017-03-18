package cvut.fit.di.testEntity.constructor.exception;

import cvut.fit.di.testEntity.constructor.AConst;
import cvut.fit.di.testEntity.constructor.BConst;

import javax.inject.Inject;

/**
 * @author Samuel Butta
 */
public class ConstWithMoreInjectConstructors {

    private AConst aConst;

    private BConst bConst;


    @Inject
    public ConstWithMoreInjectConstructors(BConst bConst) {
        this.bConst = bConst;
    }

    @Inject
    public ConstWithMoreInjectConstructors(AConst aConst, BConst bConst) {
        this.aConst = aConst;
        this.bConst = bConst;
    }

}
