package cvut.fit.di.container.constructor.circular.cycle;

/**
 * @author Samuel Butta
 */
public interface CycleA {

    CycleB getB();

    CycleC getC();

    String test();
}
