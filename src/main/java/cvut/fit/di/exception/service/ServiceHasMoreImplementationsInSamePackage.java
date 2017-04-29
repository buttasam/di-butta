package cvut.fit.di.exception.service;

/**
 * @author Samuel Butta
 */
public class ServiceHasMoreImplementationsInSamePackage extends RuntimeException {


    public ServiceHasMoreImplementationsInSamePackage() {
        super("Service has more implementation in same package");
    }
}
