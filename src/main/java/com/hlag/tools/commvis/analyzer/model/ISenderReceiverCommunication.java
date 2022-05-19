package com.hlag.tools.commvis.analyzer.model;

import java.util.UUID;

/**
 * The top level interface for all producers and consumers or senders and receivers.
 */
public interface ISenderReceiverCommunication {
    /**
     * @return an identifier for the object. Globally unique.
     */
    String getId();

    void visit(AbstractCommunicationModelVisitor visitor);
}