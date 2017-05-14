package cvut.fit.di.exception;

/**
 * K vyhozeni dojde, pokud se nepodori vytvorit instanci.
 *
 * @author Samuel Butta
 */
public class InstanceCanNotBeCreated extends RuntimeException {


    public InstanceCanNotBeCreated() {
        super("Instance can not be created");
    }
}
