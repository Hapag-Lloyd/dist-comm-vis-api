package com.hlag.tools.commvis.analyzer.model;

import com.google.gson.annotations.SerializedName;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Value;

/**
 * A producer for SQS messages.
 */
@Value
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class SqsProducer implements ISenderReceiverCommunication, IProducer {
    /**
     * the class name where the producer was found.
     */
    @SerializedName(value="class_name")
    String className;

    /**
     * the method name were the producer was found.
     */
    @SerializedName(value="method_name")
    String methodName;

    /**
     * the queue the messages are sent to.
     */
    @SerializedName(value="queue_name")
    String queueName;

    /**
     * The project id of the referenced project.
     */
    @SerializedName(value="destination_project_id")
    String destinationProjectId;

    /**
     * internal id of this node
     */
    @SerializedName(value="id")
    String id;

    @Override
    public void visit(AbstractCommunicationModelVisitor visitor) {
        visitor.visit(this);
    }
}
