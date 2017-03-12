package cvut.fit.di.testEntity;

import javax.inject.Singleton;

/**
 * @author Samuel Butta
 */
@Singleton
public class ButtonDao {


    public void print() {
        System.out.println("button dao");
    }

}
