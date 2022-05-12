package com.hlag.tools.commvis.analyzer.model;

/**
 * The top level interface for all producers and consumers or senders and receivers.
 */
public interface ISenderReceiverCommunication {
    /**
     * @return an identifier valid within a model
     */
    Long getId();

    void visit(AbstractCommunicationModelVisitor visitor);
}