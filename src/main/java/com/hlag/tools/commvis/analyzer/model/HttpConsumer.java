package com.hlag.tools.commvis.analyzer.model;

import lombok.*;

/**
 * An endpoint which can receive http(s) messages. Typically, a REST API.
 */
@Value
public class HttpConsumer implements ISenderReceiverCommunication {
    private final String className;
    private final String methodName;

    private final String type;
    private final String path;

    @Override
    public void visit(AbstractCommunicationModelVisitor visitor) {
        visitor.visit(this);
    }
}
