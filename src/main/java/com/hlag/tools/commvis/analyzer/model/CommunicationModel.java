package com.hlag.tools.commvis.analyzer.model;

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
    private String projectId;
    /**
     * A name for the project. Just for information.
     */
    private String projectName;
    /**
     * All sender and receiver endpoints found.
     */
    private Collection<ISenderReceiverCommunication> endpoints;

    public void visit(AbstractCommunicationModelVisitor visitor) {
        visitor.visit(this);

        endpoints.forEach(e -> e.visit(visitor));
    }
}
