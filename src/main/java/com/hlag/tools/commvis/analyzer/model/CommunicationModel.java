package com.hlag.tools.commvis.analyzer.model;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.Collection;

/**
 * Base class for the entire communication model, e.g. senders and receivers.
 *
 * The model is built by various services implementing the {@link com.hlag.tools.commvis.analyzer.service.IScannerService}
 * interface.
 */
@Getter
@AllArgsConstructor
@ToString
public class CommunicationModel {
    /**
     * Identifier for the current project, e.g. gitlab project id
     */
    @SerializedName(value="project_id")
    private String projectId;

    /**
     * A name for the project. Just for information.
     */
    @SerializedName(value="project_name")
    private String projectName;

    /**
     * All HTTP receiver endpoints.
     */
    @SerializedName(value="http_consumers")
    private Collection<ISenderReceiverCommunication> httpConsumers;

    /**
     * All HTTP producers.
     */
    @SerializedName(value="http_producers")
    private Collection<ISenderReceiverCommunication> httpProducers;

    /**
     * All JMS receivers.
     */
    @SerializedName(value="jms_consumers")
    private Collection<ISenderReceiverCommunication> jmsConsumers;

    public void visit(AbstractCommunicationModelVisitor visitor) {
        visitor.visit(this);

        httpConsumers.forEach(e -> e.visit(visitor));
        httpProducers.forEach(e -> e.visit(visitor));
        jmsConsumers.forEach(e -> e.visit(visitor));
    }
}
