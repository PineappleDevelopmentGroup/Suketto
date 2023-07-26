package sh.miles.suketto.bukkit.internal.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The NMS annotation is annotated upon any class or method that required NMS to function. {@link #lastNeededVersion()}
 * indicates the highest version NMS is needed for. {@link #lowestSupportedVersion()} indicates the lowest supported
 * version the NMS supports. If a Class is annotated with this annotation it should be assumed the whole class relies on
 * NMS to function
 */
@Inherited
@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.METHOD, ElementType.CONSTRUCTOR, ElementType.TYPE})
public @interface NMS {

    /**
     * Indicates the highest supported version for the annotated element
     *
     * @return the latest needed version
     */
    String lastNeededVersion() default "1.20.1";

    /**
     * Indicates the lowest supported version for the annotated element
     *
     * @return the lowest supported version
     */
    String lowestSupportedVersion() default "1.19.4";

}
