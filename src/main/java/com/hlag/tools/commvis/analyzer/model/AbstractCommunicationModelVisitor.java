package com.hlag.tools.commvis.analyzer.model;

/**
 * A visitor which processes the {@link CommunicationModel}.
 */
public abstract class AbstractCommunicationModelVisitor {
    public abstract void visit(CommunicationModel model);

    public abstract void visit(HttpConsumer httpReceiver);
    public abstract void visit(HttpProducer httpReceiver);

    public abstract void visit(JmsReceiver jmsReceiver);

    public abstract void visit(SqsConsumer sqsConsumer);
}