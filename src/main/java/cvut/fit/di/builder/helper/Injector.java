package cvut.fit.di.builder.helper;

import cvut.fit.di.repository.store.ServiceStore;
import cvut.fit.di.repository.store.ServiceStoreFactory;

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
                    System.out.println(paramType);
                    ServiceStore serviceStore = ServiceStoreFactory.getServiceStore();
                    method.invoke(object, serviceStore.getOrCreateService(paramType).getInstance());
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
