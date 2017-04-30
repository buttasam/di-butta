package cvut.fit.di.anotation.proxy;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Specialni anotace pro rozhrani InstanceSetter.
 * Takto anotovana metoda je chapana jako setter
 * instance puvodniho objektu.
 *
 * @author Samuel Butta
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface TargetInstanceSetter {
}
