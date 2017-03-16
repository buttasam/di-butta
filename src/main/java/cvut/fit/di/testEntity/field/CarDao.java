package cvut.fit.di.testEntity.field;

import cvut.fit.di.anotation.Prototype;

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

    public void print() {
        System.out.println("car dao");
    }
}
