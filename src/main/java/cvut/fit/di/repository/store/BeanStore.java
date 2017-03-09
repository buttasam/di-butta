package cvut.fit.di.repository.store;

import cvut.fit.di.repository.entity.Bean;

import java.util.HashSet;
import java.util.Set;

/**
 * Bean je stejne jako v JavaEE definovaná - trida/sluzba kterou spravuje DI kontejner
 * <p>
 * Trida udruje informace o beans.
 */
public class BeanStore {


    /**
     * Mnozina vsech bean spravovanych DI frameworkem.
     */
    private Set<Bean> managedBeans;


    public BeanStore() {
        managedBeans = new HashSet<Bean>();
    }

}
