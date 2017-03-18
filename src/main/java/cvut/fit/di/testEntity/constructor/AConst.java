package cvut.fit.di.testEntity.constructor;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author Samuel Butta
 */
@Singleton
public class AConst {

    private BConst bConst;

    @Inject
    public AConst(BConst bConst) {
        this.bConst = bConst;
    }

    public BConst getbConst() {
        return bConst;
    }
}
