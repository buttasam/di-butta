package cvut.fit.di.testEntity;

import cvut.fit.di.anotation.Prototype;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author Samuel Butta
 */
@Singleton
public class RadioDao {

    private ButtonDao buttonDao;

    public void print() {
        System.out.println("radio dao");
    }

    @Inject
    public void setButtonDao(ButtonDao buttonDao) {
        this.buttonDao = buttonDao;
    }

    public ButtonDao getButtonDao() {
        return buttonDao;
    }
}
