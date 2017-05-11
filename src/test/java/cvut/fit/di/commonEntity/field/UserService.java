package cvut.fit.di.commonEntity.field;

import cvut.fit.di.anotation.scope.Prototype;

import javax.inject.Inject;

/**
 * @author Samuel Butta
 */
@Prototype
public class UserService {


    @Inject
    public CarDao carDao;

    private String name;

    public CarDao getCarDao() {
        return carDao;
    }


    public void print() {
        System.out.println("user service");
    }


}
