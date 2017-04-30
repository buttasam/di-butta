package cvut.fit.di.testEntity.field;

import cvut.fit.di.anotation.scope.Prototype;

import javax.inject.Inject;

/**
 * @author Samuel Butta
 */
@Prototype
public class CarDao {

    @Inject
    private RadioDao radioDao;

    public RadioDao getRadioDao() {
        return radioDao;
    }
}
