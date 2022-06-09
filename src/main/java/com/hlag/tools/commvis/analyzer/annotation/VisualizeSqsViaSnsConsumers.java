package com.hlag.tools.commvis.analyzer.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
/**
 * Used to group multiple {@link VisualizeSqsViaSnsConsumer} annotations on one element.
 */
public @interface VisualizeSqsViaSnsConsumers {
    /**
     * @return all grouped {@link VisualizeSqsViaSnsConsumer} annotations
     */
    VisualizeSqsViaSnsConsumer[] value();
}
