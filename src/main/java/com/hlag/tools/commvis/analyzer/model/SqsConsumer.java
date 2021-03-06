package com.hlag.tools.commvis.analyzer.model;

import com.google.gson.annotations.SerializedName;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Value;

/**
 * A receiver for SQS messages.
 */
@Value
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class SqsConsumer implements ISenderReceiverCommunication, IConsumer {
    @SerializedName(value="class_name")
    String className;

    @SerializedName(value="method_name")
    String methodName;

    @SerializedName(value="queue_name")
    String queueName;

    @SerializedName(value="id")
    String id;

    @Override
    public void visit(AbstractCommunicationModelVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public boolean isProducedBy(IProducer producer) {
        if (producer instanceof SqsProducer) {
            SqsProducer sqsProducer = (SqsProducer) producer;

            return queueName.equals(sqsProducer.getQueueName());
        }

        return false;
    }
}
