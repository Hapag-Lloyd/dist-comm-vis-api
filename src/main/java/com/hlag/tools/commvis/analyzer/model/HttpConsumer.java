package com.hlag.tools.commvis.analyzer.model;

import com.google.gson.annotations.SerializedName;
import lombok.*;

import java.util.UUID;

/**
 * An endpoint which can receive http(s) messages. Typically, a REST API.
 */
@Value
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class HttpConsumer implements ISenderReceiverCommunication {
    @SerializedName(value="class_name")
    String className;
    @SerializedName(value="method_name")
    String methodName;

    @SerializedName(value="type")
    String type;
    @SerializedName(value="path")
    String path;

    @SerializedName(value="id")
    UUID id = UUID.randomUUID();

    @Override
    public void visit(AbstractCommunicationModelVisitor visitor) {
        visitor.visit(this);
    }
}
