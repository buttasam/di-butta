package cvut.fit.di.exception.service;

/**
 * @author Samuel Butta
 */
public class ServiceHasNoImplementationInSamePackage extends RuntimeException {


    public ServiceHasNoImplementationInSamePackage() {
        super("Service has no implementation in same package");
    }
}
