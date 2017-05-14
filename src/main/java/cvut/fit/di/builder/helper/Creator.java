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
     * @param <T>   typovy parametr
     * @return nove vytvorena instance
     */
    @SuppressWarnings("unchecked")
    public <T> T createNewInstance(Class clazz) {
        Class<?> c;
        try {
            c = Class.forName(clazz.getName());
            Constructor<?> cons = c.getConstructor();
            return (T) cons.newInstance();
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            throw new InstanceCanNotBeCreated();
        }
    }

    /**
     * Vytvori novou instanci podle daneho konstruktoru a seznamu parametru.
     *
     * @param constructor konstruktor
     * @param params      parametry vkladane do konstruktoru
     * @param <T>         typovy parametr
     * @return nove vytvorena instance
     */
    @SuppressWarnings("unchecked")
    public <T> T createNewInstance(Constructor constructor, List<Object> params) {
        try {
            return (T) constructor.newInstance(params.toArray());
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            throw new InstanceCanNotBeCreated();
        }

    }
}
