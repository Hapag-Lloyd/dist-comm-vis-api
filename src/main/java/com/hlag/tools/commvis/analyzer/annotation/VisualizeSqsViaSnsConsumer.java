package com.hlag.tools.commvis.analyzer.annotation;

import java.lang.annotation.*;

/**
 * Annotated on methods to indicate that SQS messages are consumed from a SBS topic.
 */
@Repeatable(VisualizeSqsViaSnsConsumers.class)
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface VisualizeSqsViaSnsConsumer {
    /**
     * @return name of the SNS topic messages are received from (via subscription)
     */
    String topicName();

    /**
     * @return the name of the project the messages are received from. Just for a better visibility in the code. The value isn't used.
     */
    String projectName() default "";
}
