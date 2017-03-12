package cvut.fit.di.builder;

import cvut.fit.di.builder.helper.Creator;
import cvut.fit.di.builder.helper.Finder;
import cvut.fit.di.builder.helper.Injector;

/**
 * @author Samuel Butta
 */
public class Executor {


    private Creator creator;
    private Finder finder;
    private Injector injector;


    public Executor() {
        creator = new Creator();
        finder = new Finder();
        injector = new Injector();
    }


    /**
     * Metoda nastavy vsechny zavislosti
     * rekurzicne se hledaji zavislosti podle vstupni tridy
     * @param initClass vstupni trida
     */
    public void initObjectGraph(Class initClass) {

    }


}
