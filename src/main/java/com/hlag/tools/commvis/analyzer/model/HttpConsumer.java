package com.hlag.tools.commvis.analyzer.model;

import com.google.gson.annotations.SerializedName;
import lombok.*;

/**
 * An endpoint which can receive http(s) messages. Typically, a REST API.
 */
@Value
public class HttpConsumer implements ISenderReceiverCommunication {
    @SerializedName(value="className")
    private final String className;
    @SerializedName(value="methodName")
    private final String methodName;

    @SerializedName(value="type")
    private final String type;
    @SerializedName(value="path")
    private final String path;

    @Override
    public void visit(AbstractCommunicationModelVisitor visitor) {
        visitor.visit(this);
    }
}
