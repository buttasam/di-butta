package cvut.fit.di.repository.store;

import cvut.fit.di.repository.entity.Bean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Bean je stejne jako v JavaEE definovaná - trida/sluzba kterou spravuje DI kontejner
 * <p>
 * Trida udruje informace o beans.
 */
public class BeanStore {


    private Logger log = LoggerFactory.getLogger(BeanStore.class);

    /**
     * Mnozina vsech bean spravovanych DI frameworkem.
     */
    private Set<Bean> managedBeans;


    public BeanStore() {
        managedBeans = new HashSet<Bean>();
    }


    /**
     * Pridava do storu beanu bez implementace.
     *
     * @param beanClass
     * @param <T>
     */
    public synchronized <T> void addBean(Class<T> beanClass) {
        // pokud jiz beana ve storu je, neprida se, zaloguje se warning
        if (findBean(beanClass).isPresent()) {
            // vyhodit vyjimku
            log.warn("Bean of type {} is already in beanstore", beanClass);
        } else {
            Bean bean = new Bean(beanClass);
            managedBeans.add(bean);
        }
    }

    /**
     * Pridava beanu s implementaci.
     *
     * @param beanInterface
     * @param beanImpl
     * @param <T>
     */
    public synchronized <T> void addBean(Class<T> beanInterface, Class<? extends T> beanImpl) {
        // pokus se najit tuto beanu
        Bean bean = new Bean(beanInterface, beanImpl);

        managedBeans.add(bean);
    }

    public <T> Object getInstace(Class<T> beanClass) {
        Optional<Bean> beanOpt = findBean(beanClass);
        Bean bean = beanOpt.get();
        return bean.getInstance();
    }

    /**
     * @return Vraci pocet managovanych bean.
     */
    public int managedBeansCount() {
        return managedBeans.size();
    }


    private <T> Optional<Bean> findBean(Class<T> beanClass) {
        return managedBeans.stream().filter(b -> b.getClassImpl().equals(beanClass)).findAny();
    }
}
