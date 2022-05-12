package com.hlag.tools.commvis.analyzer.model;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.Collection;
import java.util.HashSet;

/**
 * Base class for the entire communication model, e.g. senders and receivers.
 *
 * The model is built by various services implementing the {@link com.hlag.tools.commvis.analyzer.service.IScannerService}
 * interface.
 */
@RequiredArgsConstructor
@ToString
public class CommunicationModel {
    /**
     * Identifier for the current project, e.g. gitlab project id
     */
    @SerializedName(value="project_id")
    private final String projectId;

    /**
     * A name for the project. Just for information.
     */
    @SerializedName(value="project_name")
    private final String projectName;

    @SerializedName(value="model_version")
    private final String modelVersion;

    /**
     * All HTTP receiver endpoints.
     */
    @SerializedName(value="http_consumers")
    private Collection<ISenderReceiverCommunication> httpConsumers = new HashSet<>();

    /**
     * All HTTP producers.
     */
    @SerializedName(value="http_producers")
    private Collection<ISenderReceiverCommunication> httpProducers = new HashSet<>();

    /**
     * All JMS receivers.
     */
    @SerializedName(value="jms_consumers")
    private Collection<ISenderReceiverCommunication> jmsConsumers = new HashSet<>();

    public void addHttpConsumer(ISenderReceiverCommunication consumer) {
        httpConsumers.add(consumer);
    }

    public void addHttpProducer(ISenderReceiverCommunication producer) {
        httpProducers.add(producer);
    }

    public void addJmsConsumer(ISenderReceiverCommunication consumer) {
        jmsConsumers.add(consumer);
    }

    public void visit(AbstractCommunicationModelVisitor visitor) {
        visitor.visit(this);

        httpConsumers.forEach(e -> e.visit(visitor));
        httpProducers.forEach(e -> e.visit(visitor));
        jmsConsumers.forEach(e -> e.visit(visitor));
    }
}
