package cvut.fit.di.testEntity;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author Samuel Butta
 */
@Singleton
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
