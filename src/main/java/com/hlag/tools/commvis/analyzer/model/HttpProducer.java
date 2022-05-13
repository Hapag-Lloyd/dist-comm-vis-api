package com.hlag.tools.commvis.analyzer.model;

import com.google.gson.annotations.SerializedName;
import lombok.*;

import java.util.UUID;

/**
 * Holds the data of one method calling a http(s) endpoint.
 */
@Value
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class HttpProducer implements ISenderReceiverCommunication {
    /**
     * The full qualified classname where the producer lives.
     */
    @SerializedName(value="class_name")
    String className;

    /**
     * The method name of the producer.
     */
    @SerializedName(value="method_name")
    String methodName;

    /**
     * The HTTP method like GET or POST.
     */
    @SerializedName(value="type")
    String type;

    /**
     * The full path name called (usually without a domain).
     */
    @SerializedName(value="path")
    String path;

    /**
     * The project id of the referenced project.
     */
    @SerializedName(value="destination_project_id")
    String destinationProjectId;

    @SerializedName(value="id")
    UUID id = UUID.randomUUID();

    @Override
    public void visit(AbstractCommunicationModelVisitor visitor) {
        visitor.visit(this);
    }
}