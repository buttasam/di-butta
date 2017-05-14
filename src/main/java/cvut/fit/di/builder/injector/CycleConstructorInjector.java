package cvut.fit.di.builder.injector;

import cvut.fit.di.builder.injector.config.ConfigType;
import cvut.fit.di.exception.service.AllServiceMustImplementInterfaceException;
import cvut.fit.di.exception.service.ServiceIsNotInObjectGraphException;
import cvut.fit.di.graph.ServiceNode;
import cvut.fit.di.proxy.ProxyUtil;
import cvut.fit.di.repository.entity.Service;

import java.lang.reflect.Constructor;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Injektor pro konstruktorovou injektaz.
 * Objektovy graf muze obsahovat cyklickou zavislost.
 * Vsechny sluzby vsak musi implementovat rozhrani,
 * kvuli vytvareni proxy objektu.
 *
 * @author Samuel Butta
 */
public class CycleConstructorInjector extends Injector {


    /**
     * Pomocna trida, ktera udrzuje jiz vytvorene proxies
     */
    private Map<Class, Object> proxies = new HashMap<>();


    public CycleConstructorInjector() {
        super();
    }

    public CycleConstructorInjector(ConfigType configType) {
        super();
        this.configType = configType;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public <T> T getInstance(Class<T> initClass) throws ServiceIsNotInObjectGraphException {
        // inicializace grafu (podgrafu) v pripade introspekci
        initSubgraphByIntrospection(initClass);

        // overit zda takova trida existuje v objektovem grafu
        ServiceNode node = objectGraph.getNode(initClass);

        // overit zda vsechny sluzby implementuji rozhrani
        if (!objectGraphAPI.allServicesHasInterface()) {
            throw new AllServiceMustImplementInterfaceException();
        }

        // pokud existuje
        if (node != null) {
            // vytvoreni a ulozeni proxy
            T proxy = ProxyUtil.createProxy(initClass);
            proxies.put(initClass, proxy);

            // konstruktor s anotaci inject
            Constructor constructor = finder.findInjectedConstructor(node.getClazzImpl());

            // vsechny jeho parametry jsou proxy tridy
            List<Object> params = new ArrayList<>();

            if (constructor != null) {
                Class[] paramTypes = constructor.getParameterTypes();

                params = Arrays.stream(paramTypes).map(p -> {
                    try {
                        Object foundProxy = proxies.get(p);
                        if (foundProxy != null) {
                            return foundProxy;
                        } else {
                            return getInstance(p);
                        }
                    } catch (ServiceIsNotInObjectGraphException e) {
                        e.printStackTrace();
                        return null;
                    }
                }).collect(Collectors.toList());
            }

            // vytvoreni skutecneho objektu
            Service service = serviceStore.getOrCreateService(node);

            T target = null;
            // pokud je singleton a je jiz inicializovana vrat ji
            if (service.singletonAvailable()) {
                target = (T) service.getSingletonInstance();
            } else {
                target = (T) service.getInstance(constructor, params);
            }

            proxy = ProxyUtil.setInstance(proxy, target);

            return proxy;
        } else {
            throw new ServiceIsNotInObjectGraphException();
        }
    }
}
