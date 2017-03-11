package cvut.fit.di.builder;

import cvut.fit.di.anotation.Prototype;
import cvut.fit.di.exception.AmbiguousImplementationException;
import cvut.fit.di.exception.MissingImplementationException;
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

    /**
     * Najde ve vsech podbaliccich podle parametru packagePrefix.
     * @param packagePrefix
     * @param clazz
     * @return
     * @throws MissingImplementationException
     * @throws AmbiguousImplementationException
     */
    public Class<?> findImplementation(String packagePrefix, Class clazz) throws MissingImplementationException, AmbiguousImplementationException {
        Reflections reflections = new Reflections(packagePrefix);

        Set<Class<?>> subtypes = reflections.getSubTypesOf(clazz);

        if(subtypes.isEmpty()) {
            throw new MissingImplementationException();
        }
        if(subtypes.size() != 1) {
            throw new AmbiguousImplementationException();
        }

        return subtypes.stream().findFirst().get();
    }


    /**
     * Vyhleda implementace rozhrani
     * 
     * @param clazz
     * @return
     * @throws MissingImplementationException
     * @throws AmbiguousImplementationException
     */
    public Class<?> findImplementation(Class clazz) throws MissingImplementationException, AmbiguousImplementationException {
        String packagePrefix = clazz.getPackage().getName();

        return findImplementation(packagePrefix, clazz);
    }

}
