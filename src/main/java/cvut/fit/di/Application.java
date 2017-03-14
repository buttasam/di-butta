package cvut.fit.di;

import cvut.fit.di.container.DIContainer;
import cvut.fit.di.testEntity.UserService;

import java.lang.reflect.InvocationTargetException;


public class Application {

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {

        DIContainer container = new DIContainer();

        UserService service = (UserService) container.getInstance(UserService.class);
        service.getCarDao().getRadioDao().getButtonDao().print();

        UserService service2 = (UserService) container.getInstance(UserService.class);
        service2.getCarDao().getRadioDao().getButtonDao().print();

    }


}
