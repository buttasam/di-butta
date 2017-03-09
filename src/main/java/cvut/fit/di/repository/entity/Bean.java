package cvut.fit.di.repository.entity;

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
    }

    public BeanScope getBeanScope() {
        return beanScope;
    }

    public void setBeanScope(BeanScope beanScope) {
        this.beanScope = beanScope;
    }
}
