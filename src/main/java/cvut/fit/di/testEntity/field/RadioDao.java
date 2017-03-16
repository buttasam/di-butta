package cvut.fit.di.testEntity.field;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author Samuel Butta
 */
@Singleton
public class RadioDao {

    @Inject
    private UserService userService;

    public void print() {
        System.out.println("radio dao");
    }


    public UserService getUserService() {
        return userService;
    }
}
