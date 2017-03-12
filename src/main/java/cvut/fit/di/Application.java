package cvut.fit.di;

import cvut.fit.di.container.DIContainer;
import cvut.fit.di.testEntity.UserService;


public class Application {

    public static void main(String[] args) {

        DIContainer container = new DIContainer();

        UserService service = (UserService) container.getInstance(UserService.class);
        service.getCarDao().getRadioDao().getButtonDao().print();
    }

}
