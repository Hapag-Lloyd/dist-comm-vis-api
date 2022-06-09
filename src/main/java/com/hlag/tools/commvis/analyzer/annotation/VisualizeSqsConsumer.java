package com.hlag.tools.commvis.analyzer.annotation;

import java.lang.annotation.*;

/**
 * Annotated on methods to indicate that SQS messages are consumed.
 */
@Repeatable(VisualizeSqsConsumers.class)
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface VisualizeSqsConsumer {
    /**
     * @return name of the SQS queue messages are received from
     */
    String queueName();

    /**
     * @return the name of the project called. Just for a better visibility in the code. The value isn't used.
     */
    String projectName() default "";
}
