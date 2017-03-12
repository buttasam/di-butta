package cvut.fit.di.builder.helper;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 *
 * Trida vytvari nove instance objektu.
 *
 * @author Samuel Butta
 */
public class Creator {

    /**
     * Vytvori novou instanci podle tridy.
     * //TODO
     * zatim bere pouze konstruktor bez parametru.
     * Pokud nastane chyba vypise se vyjimka, vrati null.
     * @param clazz typ tridy
     * @return nove vytvorena instance
     */
    public Object createNewInstance(Class clazz) {
        Class<?> c = null;
        try {
            c = Class.forName(clazz.getName());
            Constructor<?> cons = c.getConstructor();
            return cons.newInstance();
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    }

}
