package com.hlag.tools.commvis.analyzer.annotation;

import com.google.gson.annotations.SerializedName;
import com.hlag.tools.commvis.analyzer.model.AbstractCommunicationModelVisitor;

import java.lang.annotation.*;

/**
 * Marks a producer for AWS SQS messages.
 */
@Repeatable(VisualizeSqsProducers.class)
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface VisualizeSqsProducer {
    /**
     * @return name of the SQS queue messages are sent to
     */
    String queueName();

    /**
     * @return the id of the project called, usually the Gitlab project id or similar
     */
    String projectId();

    /**
     * @return the name of the project called. Just for a better visibility in the code. The value isn't used.
     */
    String projectName() default "";
}
