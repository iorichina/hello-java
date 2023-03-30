package iorichina.hellojava.hellospringboot.annotation;

import iorichina.hellojava.hellospringboot.autoconfigure.condition.OnNoPropertyCondition;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
@Conditional(OnNoPropertyCondition.class)
@Repeatable(ConditionalOnNoProperties.class)
public @interface ConditionalOnNoProperty {
    /**
     * name of property
     */
    String name();

    /**
     * value of property
     */
    String value() default "";

}
