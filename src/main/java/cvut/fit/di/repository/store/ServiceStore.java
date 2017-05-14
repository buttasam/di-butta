package cvut.fit.di.repository.store;

import cvut.fit.di.exception.service.ServiceAlreadyExistsException;
import cvut.fit.di.graph.ServiceNode;
import cvut.fit.di.repository.entity.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Service je trida/sluzba kterou spravuje DI kontejner
 * Trida udruje informace o managed (spravovanych) services.
 *
 * @author Samuel Butta
 */
public class ServiceStore {


    /**
     * Mnozina vsech services spravovanych DI frameworkem.
     */
    private Set<Service> managedServices;


    public ServiceStore() {
        managedServices = new HashSet<>();
    }


    /**
     * Pridava do uloziste service bez implementace.
     *
     * @param serviceClass typ tridy
     * @param <T>          typovy parametr
     */
    public synchronized <T> void addService(Class<T> serviceClass) {
        // pokud jiz servicea ve storu je, dojte k vyhozeni vyjimky
        if (findService(serviceClass).isPresent()) {
            throw new ServiceAlreadyExistsException();
        } else {
            Service service = new Service(serviceClass);
            managedServices.add(service);
        }
    }

    /**
     * Pridava do uloziste service s implementaci.
     *
     * @param serviceInterface typ rozhrani
     * @param serviceImpl      typ implementace
     * @param <T>              typovy parametr
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
     * Ziska, pripadne vytvori a prida sluzbu pro danou implementaci.
     *
     * @param node uzel grafu
     * @return sluzba
     */
    @SuppressWarnings("unchecked")
    public Service getOrCreateService(ServiceNode node) {
        // hledana service
        Optional<Service> foundService = findService(node.getClazzImpl());
        if (!foundService.isPresent()) {
            addService(node.getClazzImpl());
        }
        foundService = findService(node.getClazzImpl());
        return foundService.get();
    }
}
