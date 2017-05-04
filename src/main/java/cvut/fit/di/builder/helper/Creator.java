package cvut.fit.di.builder.helper;

import cvut.fit.di.exception.InstanceCanNotBeCreated;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Trida vytvari nove instance objektu.
 *
 * @author Samuel Butta
 */
public class Creator {

    /**
     * Vytvori novou instanci podle tridy.
     * bere pouze konstruktor bez parametru.
     * Pokud nastane chyba vypise se vyjimka, vrati null.
     *
     * @param clazz typ tridy
     * @return nove vytvorena instance
     */
    public <T> T createNewInstance(Class clazz) {
        Class<?> c = null;
        try {
            c = Class.forName(clazz.getName());
            Constructor<?> cons = c.getConstructor();
            return (T) cons.newInstance();
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            throw new InstanceCanNotBeCreated();
        }
    }

    public <T> T createNewInstance(Constructor constructor, List<Object> params) {
        try {
            return (T) constructor.newInstance(params.toArray());
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            throw new InstanceCanNotBeCreated();
        }

    }
}
