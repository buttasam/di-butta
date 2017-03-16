package cvut.fit.di.testEntity.setter;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author Samuel Butta
 */
@Singleton
public class ButtonDao {

    private CarDao carDao;

    public void print() {
        System.out.println("button dao");
    }


    @Inject
    public void setCarDao(CarDao carDao) {
        this.carDao = carDao;
    }
}
