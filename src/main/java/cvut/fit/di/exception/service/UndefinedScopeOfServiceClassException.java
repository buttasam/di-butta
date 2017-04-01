package cvut.fit.di.exception.service;

/**
 * Znaci, ze servisni trida neni anotovana zadnym scopem.
 * Scope identifikuje, ze se jedna o sluzbu spravovanou DI kontejnerem.
 */
public class UndefinedScopeOfServiceClassException extends RuntimeException {

    public UndefinedScopeOfServiceClassException(Class clazz) {
        super("missing scope anotation in class " + clazz);
    }

}
