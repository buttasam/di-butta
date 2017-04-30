package cvut.fit.di.anotation.scope;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Definice scopu, ktery rika,
 * ze se vzdy vytvari nova instance.
 *
 * @author Samuel Butta
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Prototype {
}
