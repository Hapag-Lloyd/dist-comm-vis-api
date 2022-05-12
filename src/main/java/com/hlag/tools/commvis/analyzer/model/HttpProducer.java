package com.hlag.tools.commvis.analyzer.model;

import com.google.gson.annotations.SerializedName;
import lombok.Value;

/**
 * Holds the data of one method calling a http(s) endpoint.
 */
@Value
public class HttpProducer implements ISenderReceiverCommunication {
    /**
     * The full qualified classname where the producer lives.
     */
    @SerializedName(value="class_name")
    private final String className;

    /**
     * The method name of the producer.
     */
    @SerializedName(value="method_name")
    private final String methodName;

    /**
     * The HTTP method like GET or POST.
     */
    @SerializedName(value="type")
    private final String type;

    /**
     * The full path name called (usually without a domain).
     */
    @SerializedName(value="path")
    private final String path;

    /**
     * The project id of the referenced project.
     */
    @SerializedName(value="destination_project_id")
    private final String destinationProjectId;

    @Override
    public void visit(AbstractCommunicationModelVisitor visitor) {
        visitor.visit(this);
    }
}