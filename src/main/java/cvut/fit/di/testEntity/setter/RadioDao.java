package cvut.fit.di.testEntity.setter;

import cvut.fit.di.anotation.Prototype;

import javax.inject.Inject;

/**
 * @author Samuel Butta
 */
@Prototype
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
