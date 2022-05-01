package com.hlag.tools.commvis.analyzer.model;

/**
 * A visitor which processes the {@link CommunicationModel}.
 */
public abstract class AbstractCommunicationModelVisitor {
    public abstract void visit(CommunicationModel model);

    public abstract void visit(HttpReceiver httpReceiver);
    public abstract void visit(JmsReciever jmsReceiver);
}