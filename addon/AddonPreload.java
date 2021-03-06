package clashsoft.cslib.addon;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The class AddonPreload
 * <p>
 * Marks the {@code preLoad()} method in an {@link Addon} class
 * 
 * @author Clashsoft
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AddonPreload
{
	
}
