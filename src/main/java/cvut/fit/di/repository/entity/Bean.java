package cvut.fit.di.repository.entity;

import cvut.fit.di.anotation.Prototype;
import cvut.fit.di.builder.helper.Creator;

import javax.inject.Singleton;

/**
 * Reprezentuje Bean.
 * Udrzuje informaci o rozhrani a implementaci.
 * Muze byt pojmenovata (String).
 *
 * @author Samuel Butta
 */
public class Bean {


    /**
     * Pojmenovani beany.
     */
    private String beanName;


    /**
     * Typ rozhrani.
     */
    private Class classInterface;

    /**
     * Typ imepementace.
     */
    private Class classImpl;

    /**
     * Priznak, ktery urcuje zda ma beana rozhrani, ktere implementuje.
     */
    private boolean hasInterface;


    /**
     * Ulozena instance, pokud se jedna o singleton.
     */
    private Object singletonInstance;

    /**
     * Enum urcuje scope (dobu zivota) beany.
     */
    private BeanScope beanScope;

    private Bean() {
        // prazdny privatni konstruktor
    }

    /**
     * Volani tohoto konstruktoru znamena,
     * ze trida nema rozhrani, ktere implementuje.
     *
     * @param classImpl trida vcetne implementace
     */
    public Bean(Class classImpl) {
        this.classImpl = classImpl;
        this.hasInterface = false;
        findAndSetBeanScope(classImpl);
    }

    /**
     * Volani tohoto konstruktoru znamena,
     * ze trida nema rozhrani, ktere implementuje.
     *
     * @param classInterface rozhrani
     * @param classImpl      implementace tridy
     */
    public Bean(Class classInterface, Class classImpl) {
        this.classInterface = classInterface;
        this.classImpl = classImpl;

        this.hasInterface = true;
        findAndSetBeanScope(classImpl);
    }

    /**
     * Podle anotace urci scope beany.
     *
     * @param clazz
     */
    private void findAndSetBeanScope(Class clazz) {
        if (clazz.isAnnotationPresent(Singleton.class)) {
            beanScope = BeanScope.SINGLETON;
        } else if (clazz.isAnnotationPresent(Prototype.class)) {
            beanScope = BeanScope.PROTOTYPE;
        }
    }

    public BeanScope getBeanScope() {
        return beanScope;
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

        switch (beanScope) {
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


}
