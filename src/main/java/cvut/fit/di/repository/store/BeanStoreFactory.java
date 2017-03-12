package cvut.fit.di.repository.store;

/**
 * Bean je stejne jako v JavaEE definovaná - trida/sluzba kterou spravuje DI kontejner
 * <p>
 * Trida udruje informace o beans.
 */
public class BeanStoreFactory {


    private static BeanStore instance = null;


    public static BeanStore getBeanStore() {
        if(instance == null) {
            instance = new BeanStore();
        }

        return instance;
    }

}
