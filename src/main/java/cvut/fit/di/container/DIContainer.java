package cvut.fit.di.container;

import cvut.fit.di.builder.Context;
import cvut.fit.di.builder.injector.Injector;
import cvut.fit.di.builder.injector.SetterInjector;
import cvut.fit.di.builder.injector.config.ConfigType;
import cvut.fit.di.graph.ObjectGraphAPI;

import java.lang.reflect.InvocationTargetException;

/**
 * Hlavni trida, ktera reprezentuje DI kontejner.
 * Pouziti muze byt nasledujici.
 * DIContainer container = new DIContainer();
 * Sluzba se z kontejneru ziska metodou getInstance.
 * container.getInstance(MyService.class);
 *
 * @author Samuel Butta
 */
public class DIContainer {


    /**
     * Udruzje kontext, tedy predevsim instanci obejktoveho grafu a uloziste sluzeb.
     * Dale obsahuje pomocne sluzby.
     */
    private Context context;

    /**
     * Injektor, diky polymorfismu rozlisi o jaky typ injektaze se jedna.
     */
    private final Injector injector;

    /**
     * API nad objektovym grafem.
     */
    private ObjectGraphAPI objectGraphAPI;

    /**
     * Inicializace probiha po zavolani konstruktoru.
     * Vychozi injektor je nastaven na SetterInjector a automatickou konfiguraci.
     */
    public DIContainer() {
        this.injector = new SetterInjector();
        init();
    }


    /**
     * @param injector instance injektoru, podle ktere se rozlisuje typ injektaze
     */
    public DIContainer(Injector injector) {
        this.injector = injector;
        init();
    }

    /**
     * Injektor je nastaven na vychozi - setter inijnection.
     *
     * @param configType typ konfigurace
     */
    public DIContainer(ConfigType configType) {
        this.injector = new SetterInjector();
        injector.setConfigType(configType);
        init();
    }

    /**
     * Zakladni inicializacni metoda.
     */
    private void init() {
        context = new Context(injector);
        objectGraphAPI = new ObjectGraphAPI(context.getObjectGraph());
    }


    /**
     * Zakladni metoda pro ziskani instance
     *
     * @param clazz typ instance
     * @param <T>   typovy parametr
     * @return instanci tridy
     */
    public <T> T getInstance(Class<T> clazz) {
        return context.getInstance(clazz);
    }


    /**
     * Vraci API nad objektovym grafem.
     *
     * @return isntance objektoveho grafu
     */
    public ObjectGraphAPI getAPI() {
        return objectGraphAPI;
    }

}
