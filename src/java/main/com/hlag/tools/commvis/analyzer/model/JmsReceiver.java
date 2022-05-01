package com.hlag.tools.commvis.analyzer.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * A receiver for JMS messages.
 */
@AllArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class JmsReceiver implements ISenderReceiverCommunication {
    private String className;
    // e.g. "javax.jms.Queue"
    private String destinationType;
    // e.g. "jms/catalogs/customer"
    private String destination;

    @Override
    public void visit(AbstractCommunicationModelVisitor visitor) {
        visitor.visit(this);
    }
}
