package com.hlag.tools.commvis.analyzer.model;

/**
 * An endpoint receiving http(s) traffic from the outside.
 */
public interface IHttpReceiver extends ISenderReceiverCommunication {
    /**
     * @return the full qualified classname which hosts the receiver.
     */
    String getClassName();

    /**
     * @return the method name which processes the incoming data of the endpoint.
     */
    String getMethodName();

    /**
     * @return the full path which is used to reach the endpoint.
     */
    String getPath();

    /**
     * @return the http method, e.g. GET, POST, ...
     */
    String getType();

    default void visit(AbstractCommunicationModelVisitor visitor) {
        visitor.visit(this);
    }
}
