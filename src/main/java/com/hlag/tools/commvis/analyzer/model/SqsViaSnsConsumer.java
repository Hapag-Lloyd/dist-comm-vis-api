package com.hlag.tools.commvis.analyzer.model;

import com.google.gson.annotations.SerializedName;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Value;

/**
 * A receiver for SQS messages via SNS topics.
 */
@Value
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class SqsViaSnsConsumer implements ISenderReceiverCommunication, IConsumer {
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
        if (producer instanceof SnsProducer) {
            SnsProducer snsProducer = (SnsProducer) producer;

            return topicName.equals(snsProducer.getTopicName());
        }

        return false;
    }
}
