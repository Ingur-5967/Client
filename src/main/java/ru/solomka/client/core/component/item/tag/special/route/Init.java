package ru.solomka.client.core.component.item.tag.special.route;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Init {
    String value() default "";
    Class<?> clazz() default Class.class;
    String field() default "";
    String method() default "";
}
