package com.hlag.tools.commvis.analyzer.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Used to group multiple {@link VisualizeSqsProducer} annotations on one element.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface VisualizeSqsProducers {
    /**
     * @return all grouped {@link VisualizeSqsProducer} annotations
     */
    VisualizeSqsProducer[] value();
}
