package cvut.fit.di.testEntity.setter;

import cvut.fit.di.anotation.Prototype;

import javax.inject.Inject;

/**
 * @author Samuel Butta
 */
@Prototype
public class CarDao {

    private RadioDao radioDao;


    public void print() {
        System.out.println("car dao test");
    }

    @Inject
    public void setRadioDao(RadioDao radioDao) {
        this.radioDao = radioDao;
    }

    public RadioDao getRadioDao() {
        return radioDao;
    }
}
