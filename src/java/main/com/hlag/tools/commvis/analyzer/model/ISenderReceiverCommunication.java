package com.hlag.tools.commvis.analyzer.model;

/**
 * The top level interface for all producers and consumers or senders and receivers.
 */
public interface ISenderReceiverCommunication {
    void visit(AbstractCommunicationModelVisitor visitor);
}