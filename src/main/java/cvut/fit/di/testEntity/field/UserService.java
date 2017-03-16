package cvut.fit.di.testEntity.field;

import cvut.fit.di.anotation.Prototype;
import cvut.fit.di.testEntity.setter.UserDao;

import javax.inject.Inject;

/**
 * @author Samuel Butta
 */
@Prototype
public class UserService {


    @Inject
    private CarDao carDao;

    private String name;

    public CarDao getCarDao() {
        return carDao;
    }

}
