package cvut.fit.di.builder.injector.cofig;

/**
 * Vytvovy typ urcuje, jak bude sestavovan objektovy graf.
 *
 * @author Samuel Butta
 */
public enum ConfigType {


    /**
     * Bere v potaz pouze rucne pridane zavislosti.
     */
    MANUALLY,

    /**
     * Pokusi se dohledat vsechny potomky (zavislost) pomoci reflexe.
     */
    INTROSPECTION

}
