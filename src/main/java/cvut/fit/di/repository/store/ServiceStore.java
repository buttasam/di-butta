package cvut.fit.di.repository.store;

import cvut.fit.di.repository.entity.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Service je stejne jako v JavaEE definovaná - trida/sluzba kterou spravuje DI kontejner
 * <p>
 * Trida udruje informace o managed (spravovanych) services.
 */
public class ServiceStore {


    private Logger log = LoggerFactory.getLogger(ServiceStore.class);

    /**
     * Mnozina vsech services spravovanych DI frameworkem.
     */
    private Set<Service> managedServices;


    public ServiceStore() {
        managedServices = new HashSet<>();
    }


    /**
     * Pridava do storu service bez implementace.
     *
     * @param serviceClass
     * @param <T>
     */
    public synchronized <T> void addService(Class<T> serviceClass) {
        // pokud jiz servicea ve storu je, neprida se, zaloguje se warning
        if (findService(serviceClass).isPresent()) {
            // vyhodit vyjimku
            log.warn("Service of type {} is already in servicestore", serviceClass);
        } else {
            Service service = new Service(serviceClass);
            managedServices.add(service);
        }
    }

    /**
     * Pridava service s implementaci.
     *
     * @param serviceInterface
     * @param serviceImpl
     * @param <T>
     */
    public synchronized <T> void addService(Class<T> serviceInterface, Class<? extends T> serviceImpl) {
        // pokus se najit tuto service
        Service service = new Service(serviceInterface, serviceImpl);

        managedServices.add(service);
    }

    public <T> Object getInstance(Class<T> serviceClass) {
        Optional<Service> serviceOpt = findService(serviceClass);
        Service service = serviceOpt.get();
        return service.getInstance();
    }

    /**
     * @return Vraci pocet managovanych service.
     */
    public int managedServicesCount() {
        return managedServices.size();
    }


    private <T> Optional<Service> findService(Class<T> serviceClass) {
        return managedServices.stream().filter(b -> b.getClassImpl().equals(serviceClass)).findFirst();
    }

    /**
     * Pokusi se najit v ulozisti serviceu.
     * Pokud jiz existuje vrati jeji instanci.
     * Pokud ne, servicea se prida a vrati jeji instance.
     *
     * TODO zatim bez rozhrani
     * @param clazz
     * @return
     */
    public Object getOrCreateInstance(Class clazz) {
        return getOrCreateService(clazz).getInstance();
    }

    /**
     * Pokusi se najit v ulozisti service.
     * Pokud jiz existuje vrati jeji instanci.
     * Pokud ne, servicea se prida a vrati jeji instance.
     *
     * TODO zatim bez rozhrani
     * @param clazz
     * @return
     */
    public Service getOrCreateService(Class clazz) {
        // hledana service
        Optional<Service> foundService = findService(clazz);
        if(!foundService.isPresent()) {
            addService(clazz);
        }
        foundService = findService(clazz);
        return  foundService.get();
    }
}
