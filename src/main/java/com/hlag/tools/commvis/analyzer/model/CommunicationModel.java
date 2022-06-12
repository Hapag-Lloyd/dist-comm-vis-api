package com.hlag.tools.commvis.analyzer.model;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.Collection;
import java.util.HashSet;

/**
 * Base class for the entire communication model, e.g. senders and receivers.
 * <p>
 * The model is built by various services implementing the {@link com.hlag.tools.commvis.analyzer.service.IScannerService}
 * interface.
 */
@RequiredArgsConstructor
@ToString
public class CommunicationModel {
    private static final String NOT_SET = "not-set";

    /**
     * Identifier for the current project, e.g. gitlab project id
     */
    @Getter
    @SerializedName(value = "project_id")
    private final String projectId;

    /**
     * A name for the project. Just for information.
     */
    @Getter
    @SerializedName(value = "project_name")
    private final String projectName;

    @SerializedName(value = "model_version")
    private final String modelVersion;

    /**
     * All HTTP consumers.
     */
    @SerializedName(value = "http_consumers")
    private Collection<HttpConsumer> httpConsumers = new HashSet<>();

    /**
     * All HTTP producers.
     */
    @SerializedName(value = "http_producers")
    private Collection<HttpProducer> httpProducers = new HashSet<>();

    /**
     * All JMS consumers.
     */
    @SerializedName(value = "jms_consumers")
    private Collection<JmsReceiver> jmsConsumers = new HashSet<>();

    /**
     * All SQS consumers.
     */
    @SerializedName(value = "sqs_consumers")
    private Collection<SqsConsumer> sqsConsumers = new HashSet<>();

    /**
     * All SQS via SNS consumers.
     */
    @SerializedName(value = "sqs_via_sns_consumers")
    private Collection<SqsViaSnsConsumer> sqsViaSnsConsumers = new HashSet<>();

    /**
     * All SQS producers.
     */
    @SerializedName(value = "sqs_producers")
    private Collection<SqsProducer> sqsProducers = new HashSet<>();

    /**
     * All SNS producers.
     */
    @SerializedName(value = "sns_producers")
    private Collection<SnsProducer> snsProducers = new HashSet<>();

    /**
     * All SNS producers.
     */
    @SerializedName(value = "kafka_producers")
    private Collection<KafkaProducer> kafkaProducers = new HashSet<>();

    /**
     * All SQS consumers.
     */
    @SerializedName(value = "kafka_consumers")
    private Collection<KafkaConsumer> kafkaConsumers = new HashSet<>();

    private CommunicationModel() {
        // for GSON deserialize
        projectId = NOT_SET;
        projectName = NOT_SET;
        modelVersion = NOT_SET;
    }

    public <T extends ISenderReceiverCommunication> void addSenderReceiver(T endpoint) {
        if (endpoint instanceof HttpConsumer) {
            httpConsumers.add((HttpConsumer) endpoint);
        } else if (endpoint instanceof HttpProducer) {
            httpProducers.add((HttpProducer) endpoint);
        } else if (endpoint instanceof JmsReceiver) {
            jmsConsumers.add((JmsReceiver) endpoint);
        } else if (endpoint instanceof SqsConsumer) {
            sqsConsumers.add((SqsConsumer) endpoint);
        } else if (endpoint instanceof SqsProducer) {
            sqsProducers.add((SqsProducer) endpoint);
        } else if (endpoint instanceof SnsProducer) {
            snsProducers.add((SnsProducer) endpoint);
        } else if (endpoint instanceof SqsViaSnsConsumer) {
            sqsViaSnsConsumers.add((SqsViaSnsConsumer) endpoint);
        } else if (endpoint instanceof KafkaProducer) {
            kafkaProducers.add((KafkaProducer) endpoint);
        } else if (endpoint instanceof KafkaConsumer) {
            kafkaConsumers.add((KafkaConsumer) endpoint);
        } else {
            throw new IllegalStateException(String.format("We have no endpoints of type %s", endpoint.getClass().getCanonicalName()));
        }
    }

    public void visit(AbstractCommunicationModelVisitor visitor) {
        visitor.visit(this);

        httpConsumers.forEach(e -> e.visit(visitor));
        httpProducers.forEach(e -> e.visit(visitor));
        jmsConsumers.forEach(e -> e.visit(visitor));
        sqsConsumers.forEach(e -> e.visit(visitor));
        sqsProducers.forEach(e -> e.visit(visitor));
        snsProducers.forEach(e -> e.visit(visitor));
        sqsViaSnsConsumers.forEach(e -> e.visit(visitor));
        kafkaProducers.forEach(e -> e.visit(visitor));
        kafkaConsumers.forEach(e -> e.visit(visitor));
    }
}
