package com.hlag.tools.commvis.analyzer.annotation;

import java.lang.annotation.*;

/**
 * Annotated on methods to indicate that Kafka messages are consumed.
 */
@Repeatable(VisualizeKafkaConsumers.class)
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface VisualizeKafkaConsumer {
    /**
     * @return name of the Kafka topic messages are received from
     */
    String topicName();

    /**
     * @return the name of the project the messages are received from. Just for a better visibility in the code. The value isn't used.
     */
    String projectName() default "";
}
