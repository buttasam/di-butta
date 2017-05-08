package cvut.fit.di.util;

import cvut.fit.di.exception.service.ServiceAndImplementationDoesNotMatchException;
import cvut.fit.di.exception.service.ServiceImplementsMoreInterfacesException;
import cvut.fit.di.exception.service.ServiceMustImplementInterfaceException;
import cvut.fit.di.graph.ObjectGraph;

/**
 * Pomocna trida pro overeni ruznych podminek.
 */
public class Validator {

    /**
     * Overuje zda je trida implementaci uvedenteho rozhrani.
     *
     * @param clazzInterface rozhrani
     * @param clazzImpl      implementace
     * @return true pokud ano, jinak vyhuzje prislusnou vyjimku
     */
    public static boolean isClazzImplementationOfInterface(Class clazzInterface, Class clazzImpl) {
        Class[] interfaces = clazzImpl.getInterfaces();

        if (interfaces.length == 0) {
            throw new ServiceMustImplementInterfaceException();
        }
        if (interfaces.length != 1) {
            throw new ServiceImplementsMoreInterfacesException();
        }
        if (!interfaces[0].equals(clazzInterface)) {
            throw new ServiceAndImplementationDoesNotMatchException();
        }

        return true;
    }

}
