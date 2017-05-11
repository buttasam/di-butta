package cvut.fit.di.container;

import cvut.fit.di.builder.Context;
import cvut.fit.di.builder.injector.Injector;
import cvut.fit.di.builder.injector.SetterInjector;
import cvut.fit.di.builder.injector.config.ConfigType;
import cvut.fit.di.graph.ObjectGraphAPI;

import java.lang.reflect.InvocationTargetException;

/**
 *
 * Hlavni trida, ktera reprezentuje kontejner.
 *
 * Pouziti muze byt nasledujici.
 * DIContainer container = new DIContainer();
 *
 * Sluzba se z kontejneru ziska metodou getInstance.
 * container.getInstance(MyService.class);
 *
 * @author Samuel Butta
 */
public class DIContainer {


    /**
     * Servisni trida, ktera predava
     */
    private Context context;

    /**
     * Injektor, diky polymorfismu rozlisi o jaky typ injektaze se jedna.
     */
    private final Injector injector;

    /**
     * API nad objektovym grafem
     */
    private ObjectGraphAPI objectGraphAPI;

    /**
     * Inicializace probiha po zavolani konstruktoru.
     * Vychozi injektor je nastaven na SetterInjector
     */
    public DIContainer() {
        this.injector = new SetterInjector();
        init();
    }


    /**
     *
     * @param injector
     */
    public DIContainer(Injector injector) {
        this.injector = injector;
        init();
    }

    public DIContainer(ConfigType configType) {
        this.injector = new SetterInjector();
        injector.setConfigType(configType);
        init();
    }

    /**
     * Zakladni inicializacni metoda.
     * Vola buildera, ktery vytvori objektovy graf.
     */
    private void init() {
        context = new Context(injector);
        objectGraphAPI = new ObjectGraphAPI(context.getObjectGraph());
    }


    public <T> T getInstance(Class<T> clazz) {
        try {
            return context.getInstance(clazz);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    }


    public ObjectGraphAPI getAPI() {
        return objectGraphAPI;
    }
    
}
