package cvut.fit.di.testEntity.managed.injected;

import javax.inject.Inject;

/**
 * @author Samuel Butta
 */
public class UserService {


    private CarDao carDao;
    private UserDao userDao;

    private String name;

    @Inject
    public void setCarDao(CarDao carDao) {
        this.carDao = carDao;
    }

    @Inject
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Inject
    public void badSetter() {
    }

    public void setName(String name) {
        this.name = name;
    }

}
