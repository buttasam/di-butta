package cvut.fit.di;

import cvut.fit.di.container.DIContainer;
import cvut.fit.di.exception.AmbiguousConstructorException;
import cvut.fit.di.testEntity.constructor.AConst;
import cvut.fit.di.testEntity.field.UserService;

import java.lang.reflect.InvocationTargetException;


public class Application {

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, AmbiguousConstructorException, InstantiationException {

        DIContainer container = new DIContainer();

/*        UserService service = (UserService) container.getInstanceByFields(UserService.class);
        service.getCarDao().print();

        service.getCarDao().getRadioDao().getUserService().print();

        UserService service2 = (UserService) container.getInstanceByFields(UserService.class);
        service2.getCarDao().print();

        service2.getCarDao().getRadioDao().getUserService().print();*/

        AConst aConst = (AConst) container.getInstanceByConstructor(AConst.class);

        aConst.getbConst().print();


    }


}
