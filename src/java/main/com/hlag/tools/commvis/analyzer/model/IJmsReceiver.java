package com.hlag.tools.commvis.analyzer.model;

/**
 * An endpoint receiving jms messages.
 */
public interface IJmsReceiver extends ISenderReceiverCommunication {
    /**
     * @return the full qualified classname which hosts the receiver.
     */
    String getClassName();

    /**
     * @return the queue/topic the endpoint is connected to
     */
    String getDestination();

    /**
     * @return identifies the type of the destination, e.g. queue, topic
     */
    String getDestinationType();

    default void visit(AbstractCommunicationModelVisitor visitor) {
        visitor.visit(this);
    }
}
