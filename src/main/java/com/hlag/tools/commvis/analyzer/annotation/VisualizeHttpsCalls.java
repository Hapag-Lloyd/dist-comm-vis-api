package com.hlag.tools.commvis.analyzer.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
/**
 * Used to group multiple {@link VisualizeHttpsCall} annotations on one element.
 */
public @interface VisualizeHttpsCalls {
    /**
     * @return all grouped {@link VisualizeHttpsCall} annotations
     */
    VisualizeHttpsCall[] value();
}
