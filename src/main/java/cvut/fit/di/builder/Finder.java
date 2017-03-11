package cvut.fit.di.builder;

import cvut.fit.di.anotation.Prototype;
import org.reflections.Reflections;

import java.util.Set;

/**
 *
 * Trida pomoci reflexe hleda beany
 *
 * @author Samuel Butta
 */
public class Finder {


    /**
     * Metoda najde vsechny tridy anotovane @Prototype
     *
     * @param packagePrefix
     * @return
     */
    public Set<Class<?>> findManagedBeans(String packagePrefix) {
        Reflections reflections = new Reflections(packagePrefix);

        Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(Prototype.class);

        return annotated;
    }

}
