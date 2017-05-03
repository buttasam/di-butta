package cvut.fit.di.builder.helper;

import cvut.fit.di.anotation.scope.Prototype;
import cvut.fit.di.exception.AmbiguousConstructorException;
import cvut.fit.di.exception.AmbiguousImplementationException;
import cvut.fit.di.exception.MissingImplementationException;
import org.reflections.Reflections;

import javax.inject.Inject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Trida pomoci reflexe hleda services
 *
 * @author Samuel Butta
 */
public class Finder {


    /**
     * Metoda najde vsechny tridy anotovane @Prototype
     *
     * @param packagePrefix prefix balicku v kterem se service hledaji
     * @return mnozina services
     */
    public Set<Class<?>> findManagedServices(String packagePrefix) {
        Reflections reflections = new Reflections(packagePrefix);

        Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(Prototype.class);

        return annotated;
    }

    /**
     * Najde ve vsech podbaliccich podle parametru packagePrefix.
     *
     * @param packagePrefix
     * @param clazz
     * @return
     */
    public Class<?> findImplementation(String packagePrefix, Class clazz) {
        Reflections reflections = new Reflections(packagePrefix);

        Set<Class<?>> subtypes = reflections.getSubTypesOf(clazz);

        if (subtypes.isEmpty()) {
            throw new MissingImplementationException();
        }
        if (subtypes.size() != 1) {
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
    public Class<?> findImplementation(Class clazz) {
        String packagePrefix = clazz.getPackage().getName();

        return findImplementation(packagePrefix, clazz);
    }

    /**
     * Najde a vrati mnozinu setterů anotovanych @Inject
     *
     * @param clazz
     */
    public Set<Method> findInjectedSetters(Class clazz) {
        Set<Method> methods = Arrays.stream(clazz.getMethods())
                .filter(m -> m.isAnnotationPresent(Inject.class) && m.getName().startsWith("set"))
                .collect(Collectors.toSet());

        return methods;
    }


    /**
     * Vrati mnozinu filedu @Inject
     *
     * @param clazz
     * @return
     */
    public Set<Field> findInjectedFields(Class clazz) {
        Creator creator = new Creator();

        Set<Field> fields = Arrays.stream(clazz.getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(Inject.class))
                .collect(Collectors.toSet());

        return fields;
    }

    public Set<Class<?>> findInjectedSettersDependencies(Class clazz) {
        Set<Method> setters = findInjectedSetters(clazz);

        Set<Class<?>> dependencyTypes = new HashSet<>();

        setters.forEach(s -> dependencyTypes.add(s.getParameterTypes()[0]));

        return dependencyTypes;
    }


    /**
     * @param clazz
     * @return
     * @throws AmbiguousConstructorException
     */
    public Constructor findInjectedConstructor(Class clazz) throws AmbiguousConstructorException {
        List<Constructor> constructors = Arrays.stream(clazz.getConstructors())
                .filter(c -> c.isAnnotationPresent(Inject.class))
                .collect(Collectors.toList());

        switch (constructors.size()) {
            case 0:
                return null;
            case 1:
                return constructors.get(0);
            default:
                throw new AmbiguousConstructorException();
        }
    }

}
