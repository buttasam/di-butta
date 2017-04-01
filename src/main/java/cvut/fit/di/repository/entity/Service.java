package cvut.fit.di.repository.entity;

import cvut.fit.di.anotation.Prototype;
import cvut.fit.di.builder.helper.Creator;

import javax.inject.Singleton;

/**
 * Reprezentuje Service.
 * Udrzuje informaci o rozhrani a implementaci.
 * Implementace je prave jedna.
 * Muze byt pojmenovata (String).
 *
 * @author Samuel Butta
 */
public class Service {


    // TODO
    /**
     * Pojmenovani service.
     */
    private String serviceName;


    /**
     * Typ rozhrani.
     */
    private Class classInterface;

    /**
     * Typ imepementace.
     */
    private Class classImpl;

    /**
     * Priznak, ktery urcuje zda ma servicea rozhrani, ktere implementuje.
     */
    private boolean hasInterface;


    /**
     * Ulozena instance, pokud se jedna o singleton.
     */
    private Object singletonInstance;

    /**
     * Enum urcuje scope (dobu zivota) servicey.
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
        this.hasInterface = false;
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

        this.hasInterface = true;
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
     *
     * Vraci instanci podle scopu.
     *
     * @return instance podle scopu
     */
    public Object getInstance() {
        Object result = null;

        switch (serviceScope) {
            case PROTOTYPE:
                Creator creator = new Creator();
                result = creator.createNewInstance(classImpl);
                break;
            case SINGLETON:
                result = lazySingletonInit();
                break;
        }
        return result;
    }

    /**
     *
     * Lazy inicializace instance pokud se jedna o singleton.
     *
     * @return
     */
    private Object lazySingletonInit() {
        if(singletonInstance == null) {
            Creator creator = new Creator();
            singletonInstance = creator.createNewInstance(classImpl);
        }
        return singletonInstance;
    }

    public Object getSingletonInstance() {
        return singletonInstance;
    }
}
