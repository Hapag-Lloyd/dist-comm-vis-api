package com.hlag.tools.commvis.analyzer.model;

/**
 * A visitor which processes the {@link AbstractCommunicationModel}.
 */
public abstract class AbstractCommunicationModelVisitor {
    public abstract void visit(AbstractCommunicationModel model);

    public abstract void visit(IHttpReceiver httpReceiver);
    public abstract void visit(IJmsReceiver jmsReceiver);
}