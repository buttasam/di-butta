package cvut.fit.di;

import cvut.fit.di.builder.injector.FieldInjector;
import cvut.fit.di.builder.injector.cofig.ConfigType;
import cvut.fit.di.container.DIContainer;
import cvut.fit.di.exception.AmbiguousConstructorException;
import cvut.fit.di.testEntity.constructor.AConst;
import cvut.fit.di.testEntity.constructor.AInterface;
import cvut.fit.di.testEntity.constructor.BConst;
import cvut.fit.di.testEntity.constructor.exception.AImpl;

import java.lang.reflect.InvocationTargetException;


public class Application {

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, AmbiguousConstructorException, InstantiationException {

        DIContainer container = new DIContainer(new FieldInjector(ConfigType.MANUALLY));
        container.getAPI().addService(AInterface.class, AImpl.class);
        container.getAPI().addService(AConst.class);
        container.getAPI().addService(BConst.class);

/*        UserService service = (UserService) container.getInstance(UserService.class);
        service.getCarDao().print();

        service.getCarDao().getRadioDao().print();*/

/*        UserService service2 = (UserService) container.getInstance(UserService.class);
        service2.getCarDao().print();

        service2.getCarDao().getRadioDao().getUserService().print();*/

        AConst aConst = container.getInstance(AConst.class);

        aConst.getbConst().print();


    }


}
