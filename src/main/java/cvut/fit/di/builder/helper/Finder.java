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
 * Pomocna trida pro hledani pomoci reflexe.
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

        return reflections.getTypesAnnotatedWith(Prototype.class);
    }

    /**
     * Najde ve vsech podbaliccich podle parametru packagePrefix.
     *
     * @param packagePrefix prefix balicku, kterem je implementace hledana
     * @param clazz         typ rozhrani
     * @return prislusna implementace
     */
    @SuppressWarnings("unchecked")
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
     * Vyhleda implementace rozhrani v balicku, v kterem se nachazi prislusna trida.
     *
     * @param clazz typ rozhrani
     * @return implementace
     * @throws MissingImplementationException   chybejici implemetace
     * @throws AmbiguousImplementationException vice nez jedna implementace
     */
    public Class<?> findImplementation(Class clazz) {
        String packagePrefix = clazz.getPackage().getName();

        return findImplementation(packagePrefix, clazz);
    }


    /**
     * Najde a vrati mnozinu setterů anotovanych @Inject
     *
     * @param clazz typ tridy
     * @return mnozina setteru
     */
    public Set<Method> findInjectedSetters(Class clazz) {
        Set<Method> methods = Arrays.stream(clazz.getMethods())
                .filter(m -> m.isAnnotationPresent(Inject.class) && m.getName().startsWith("set"))
                .collect(Collectors.toSet());

        return methods;
    }


    /**
     * Vrati mnozinu filed s anotaci Inject
     *
     * @param clazz typ tridy
     * @return mnozina field
     */
    public Set<Field> findInjectedFields(Class clazz) {
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
     * Najde konstruktor s anotaci Inject.
     *
     * @param clazz typ tridy
     * @return konstruktor
     * @throws AmbiguousConstructorException je nalezeno vice konstruktoru s anotaci Inject
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
