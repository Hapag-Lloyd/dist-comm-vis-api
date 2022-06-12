package com.hlag.tools.commvis.analyzer.annotation;

import java.lang.annotation.*;

/**
 * Marks a producer for Kafka messages.
 */
@Repeatable(VisualizeKafkaProducers.class)
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface VisualizeKafkaProducer {
    /**
     * @return name of the topic messages are sent to
     */
    String topicName();

    /**
     * @return the id of the project called, usually the Gitlab project id or similar
     */
    String projectId();

    /**
     * @return the name of the project called. Just for a better visibility in the code. The value isn't used.
     */
    String projectName() default "";
}
