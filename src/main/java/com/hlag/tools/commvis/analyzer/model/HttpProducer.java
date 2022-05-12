package com.hlag.tools.commvis.analyzer.model;

import lombok.Value;

/**
 * Holds the data of one method calling a http(s) endpoint.
 */
@Value
public class HttpProducer implements ISenderReceiverCommunication {
    /**
     * The full qualified classname where the producer lives.
     */
    private final String className;
    /**
     * The method name of the producer.
     */
    private final String methodName;

    /**
     * The HTTP method like GET or POST.
     */
    private final String type;
    /**
     * The full path name called (usually without a domain).
     */
    private final String path;
    /**
     * The project id of the referenced project.
     */
    private final String destinationProjectId;

    @Override
    public void visit(AbstractCommunicationModelVisitor visitor) {
        visitor.visit(this);
    }
}