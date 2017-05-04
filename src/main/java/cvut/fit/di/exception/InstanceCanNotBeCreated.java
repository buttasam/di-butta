package cvut.fit.di.exception;

/**
 * @author Samuel Butta
 */
public class InstanceCanNotBeCreated extends RuntimeException {


    public InstanceCanNotBeCreated() {
        super("Instance can not be created");
    }
}
