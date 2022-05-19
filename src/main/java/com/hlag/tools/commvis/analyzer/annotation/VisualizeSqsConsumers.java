package com.hlag.tools.commvis.analyzer.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
/**
 * Used to group multiple {@link VisualizeSqsConsumer} annotations on one element.
 */
public @interface VisualizeSqsConsumers {
    /**
     * @return all grouped {@link VisualizeSqsConsumer} annotations
     */
    VisualizeSqsConsumer[] value();
}
