package cvut.fit.di.repository.store;

/**
 * Service je stejne jako v JavaEE definovaná - trida/sluzba kterou spravuje DI kontejner
 * <p>
 * Trida udruje informace o services.
 */
public class ServiceStoreFactory {


    private static ServiceStore instance = null;


    public static ServiceStore getServiceStore() {
        if(instance == null) {
            instance = new ServiceStore();
        }

        return instance;
    }

}
