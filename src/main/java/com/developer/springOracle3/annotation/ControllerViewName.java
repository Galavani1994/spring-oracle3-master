package com.developer.springOracle3.annotation;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ControllerViewName {
    String value() default "";
}
