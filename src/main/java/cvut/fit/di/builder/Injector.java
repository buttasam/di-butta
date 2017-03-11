package cvut.fit.di.builder;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;

/**
 *
 * Trida injektuje zavislosti
 *
 * @author Samuel Butta
 */
public class Injector {


    /**
     *
     * @param object instace, ktera ma byt nasetovana
     * @param setters
     * @return
     */
    public Object injectSetterDependencies(Object object, Set<Method> setters) {

        Creator creator = new Creator();

        setters.stream().forEach(method -> {
            // vezmi vsechny parametry v metode
            Class<?>[] paramTypes = method.getParameterTypes();

            try {
                // TODO napojit na kontejner
                // pro vsechny typy parametru vytvor novou instanci
                for (Class paramType: paramTypes) {
                    System.out.println(method.getName());
                    method.invoke(object, creator.createNewInstance(paramType));
                }

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

        });
        return object;
    }

}
