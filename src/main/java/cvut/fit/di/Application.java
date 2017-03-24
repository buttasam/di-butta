package cvut.fit.di;

import cvut.fit.di.builder.injector.ConstructorInjector;
import cvut.fit.di.container.DIContainer;
import cvut.fit.di.exception.AmbiguousConstructorException;
import cvut.fit.di.testEntity.constructor.AConst;
import cvut.fit.di.testEntity.constructor.AInterface;
import cvut.fit.di.testEntity.constructor.exception.AImpl;
import cvut.fit.di.testEntity.field.UserService;

import java.lang.reflect.InvocationTargetException;


public class Application {

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, AmbiguousConstructorException, InstantiationException {

        DIContainer container = new DIContainer(new ConstructorInjector());
        container.getAPI().addClazzWithImpl(AInterface.class, AImpl.class);

/*        UserService service = (UserService) container.getInstance(UserService.class);
        service.getCarDao().print();

        service.getCarDao().getRadioDao().print();*/

/*        UserService service2 = (UserService) container.getInstance(UserService.class);
        service2.getCarDao().print();

        service2.getCarDao().getRadioDao().getUserService().print();*/

        AConst aConst = (AConst) container.getInstance(AConst.class);

        aConst.getbConst().print();


    }


}
