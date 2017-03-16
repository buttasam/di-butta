package cvut.fit.di;

import cvut.fit.di.container.DIContainer;
import cvut.fit.di.testEntity.field.UserService;

import java.lang.reflect.InvocationTargetException;


public class Application {

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {

        DIContainer container = new DIContainer();

        UserService service = (UserService) container.getInstanceByFields(UserService.class);
        service.getCarDao().print();

        service.getCarDao().getRadioDao().getUserService().print();


    }


}
