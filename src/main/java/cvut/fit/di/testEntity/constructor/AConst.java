package cvut.fit.di.testEntity.constructor;

import javax.inject.Inject;

/**
 * @author Samuel Butta
 */
public class AConst {

    private BConst bConst;

    @Inject
    public AConst(BConst bConst) {
        this.bConst = bConst;
    }

}
