package cvut.fit.di.repository.entity;

import cvut.fit.di.anotation.scope.Prototype;
import cvut.fit.di.builder.helper.Creator;

import javax.inject.Singleton;
import java.lang.reflect.Constructor;
import java.util.List;

/**
 * Reprezentuje ulozenou sluzbu.
 * Udrzuje informaci o rozhrani a implementaci.
 * Implementace je prave jedna.
 *
 * @author Samuel Butta
 */
public class Service<T> {


    /**
     * Typ rozhrani.
     */
    private Class classInterface;

    /**
     * Typ imepementace.
     */
    private Class classImpl;


    /**
     * Ulozena instance, pokud se jedna o singleton.
     */
    private T singletonInstance;

    /**
     * Enum urcuje scope service.
     */
    private ServiceScope serviceScope;

    private Service() {
        // prazdny privatni konstruktor
    }

    /**
     * Volani tohoto konstruktoru znamena,
     * ze trida nema rozhrani, ktere implementuje.
     *
     * @param classImpl trida vcetne implementace
     */
    public Service(Class classImpl) {
        this.classImpl = classImpl;
        findAndSetServiceScope(classImpl);
    }

    /**
     * Volani tohoto konstruktoru znamena,
     * ze trida nema rozhrani, ktere implementuje.
     *
     * @param classInterface rozhrani
     * @param classImpl      implementace tridy
     */
    public Service(Class classInterface, Class classImpl) {
        this.classInterface = classInterface;
        this.classImpl = classImpl;

        findAndSetServiceScope(classImpl);
    }

    /**
     * Podle anotace urci scope service.
     *
     * @param clazz
     */
    private void findAndSetServiceScope(Class clazz) {
        if (clazz.isAnnotationPresent(Singleton.class)) {
            serviceScope = ServiceScope.SINGLETON;
        } else if (clazz.isAnnotationPresent(Prototype.class)) {
            serviceScope = ServiceScope.PROTOTYPE;
        } else {
            // nastavi defaultni scope jako Prototype
            serviceScope = ServiceScope.PROTOTYPE;
        }
    }

    public ServiceScope getServiceScope() {
        return serviceScope;
    }

    public Class getClassInterface() {
        return classInterface;
    }

    public Class getClassImpl() {
        return classImpl;
    }

    /**
     * Vraci instanci podle scope.
     *
     * @return instance podle scope
     */
    public Object getInstance() {
        return getInstance(null, null);
    }

    public Object getInstance(Constructor constructor, List<Object> params) {
        Object result = null;

        switch (serviceScope) {
            case PROTOTYPE:
                Creator creator = new Creator();
                if (constructorHasParams(constructor, params)) {
                    result = creator.createNewInstance(constructor, params);
                } else {
                    result = creator.createNewInstance(classImpl);
                }
                break;
            case SINGLETON:
                if (constructorHasParams(constructor, params)) {
                    result = lazySingletonInit(constructor, params);
                } else {
                    result = lazySingletonInit();
                }
                break;
        }
        return result;
    }

    /**
     * Lazy inicializace instance pokud se jedna o singleton.
     *
     * @return insntace singletonu
     */
    private Object lazySingletonInit() {
        return lazySingletonInit(null, null);
    }

    private Object lazySingletonInit(Constructor constructor, List<Object> params) {
        if (singletonInstance == null) {
            Creator creator = new Creator();
            if (constructorHasParams(constructor, params)) {
                singletonInstance = creator.createNewInstance(constructor, params);
            } else {
                singletonInstance = creator.createNewInstance(classImpl);
            }
        }
        return singletonInstance;
    }

    /**
     * Vraci true, pokud konstruktor a parametry nejsou null
     * a parametry nejsou praznde
     *
     * @return
     */
    private boolean constructorHasParams(Constructor constructor, List<Object> params) {
        return constructor != null && params != null && !params.isEmpty();
    }

    public T getSingletonInstance() {
        return singletonInstance;
    }


    /**
     * @return true pokud je singleton a instance je jiz inicializovana
     */
    public boolean singletonAvailable() {
        return getServiceScope().equals(ServiceScope.SINGLETON) && getSingletonInstance() != null;
    }
}
