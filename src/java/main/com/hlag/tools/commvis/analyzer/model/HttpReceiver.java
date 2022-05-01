package com.hlag.tools.commvis.analyzer.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * An endpoint which can receive http(s) messages. Typically, a REST API.
 */
@AllArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class HttpReceiver implements ISenderReceiverCommunication {
    private String className;
    private String methodName;

    private String type;
    private String path;

    @Override
    public void visit(AbstractCommunicationModelVisitor visitor) {
        visitor.visit(this);
    }
}
