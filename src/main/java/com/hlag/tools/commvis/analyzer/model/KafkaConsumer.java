package com.hlag.tools.commvis.analyzer.model;

import com.google.gson.annotations.SerializedName;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Value;

/**
 * A receiver for Kafka messages.
 */
@Value
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class KafkaConsumer implements ISenderReceiverCommunication, IConsumer {
    @SerializedName(value="class_name")
    String className;

    @SerializedName(value="method_name")
    String methodName;

    @SerializedName(value="topic_name")
    String topicName;

    @SerializedName(value="id")
    String id;

    @Override
    public void visit(AbstractCommunicationModelVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public boolean isProducedBy(IProducer producer) {
        if (producer instanceof KafkaProducer) {
            KafkaProducer kafkaProducer = (KafkaProducer) producer;

            return topicName.equals(kafkaProducer.getTopicName());
        }

        return false;
    }
}
