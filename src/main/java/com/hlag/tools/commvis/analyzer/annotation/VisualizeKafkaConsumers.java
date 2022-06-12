package com.hlag.tools.commvis.analyzer.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
/**
 * Used to group multiple {@link VisualizeKafkaConsumer} annotations on one element.
 */
public @interface VisualizeKafkaConsumers {
    /**
     * @return all grouped {@link VisualizeKafkaConsumer} annotations
     */
    VisualizeKafkaConsumer[] value();
}
