package com.hlag.tools.commvis.analyzer.model;

import com.google.gson.annotations.SerializedName;
import lombok.*;

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
    @Getter
    @SerializedName(value="project_id")
    private final String projectId;

    /**
     * A name for the project. Just for information.
     */
    @Getter
    @SerializedName(value="project_name")
    private final String projectName;

    @SerializedName(value="model_version")
    private final String modelVersion;

    /**
     * All HTTP receiver endpoints.
     */
    @SerializedName(value="http_consumers")
    private Collection<HttpConsumer> httpConsumers = new HashSet<>();

    /**
     * All HTTP producers.
     */
    @SerializedName(value="http_producers")
    private Collection<HttpProducer> httpProducers = new HashSet<>();

    /**
     * All JMS receivers.
     */
    @SerializedName(value="jms_consumers")
    private Collection<JmsReceiver> jmsConsumers = new HashSet<>();

    private CommunicationModel() {
        // for GSON deserialize
        projectId = "not-set";
        projectName = "not-set";
        modelVersion = "not-set";
    }

    public <T extends ISenderReceiverCommunication> void addSenderReceiver(T endpoint) {
        if (endpoint instanceof HttpConsumer) {
            httpConsumers.add((HttpConsumer) endpoint);
        } else if (endpoint instanceof HttpProducer) {
            httpProducers.add((HttpProducer) endpoint);
        } else if (endpoint instanceof JmsReceiver) {
            jmsConsumers.add((JmsReceiver) endpoint);
        } else {
            throw new IllegalStateException(String.format("We have no endpoints of type %s", endpoint.getClass().getCanonicalName()));
        }
    }

    public void visit(AbstractCommunicationModelVisitor visitor) {
        visitor.visit(this);

        httpConsumers.forEach(e -> e.visit(visitor));
        httpProducers.forEach(e -> e.visit(visitor));
        jmsConsumers.forEach(e -> e.visit(visitor));
    }
}
