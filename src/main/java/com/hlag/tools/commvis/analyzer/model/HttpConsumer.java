package com.hlag.tools.commvis.analyzer.model;

import com.google.gson.annotations.SerializedName;
import lombok.*;

import java.util.UUID;

/**
 * An endpoint which can receive http(s) messages. Typically, a REST API.
 */
@Value
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class HttpConsumer implements ISenderReceiverCommunication, IConsumer {
    @SerializedName(value="class_name")
    String className;
    @SerializedName(value="method_name")
    String methodName;

    @SerializedName(value="type")
    String type;
    @SerializedName(value="path")
    String path;

    @SerializedName(value="id")
    String id;

    @Override
    public boolean isProducedBy(IProducer producer) {
        if (producer instanceof HttpProducer) {
            HttpProducer httpProducer = (HttpProducer) producer;

            return type.equals(httpProducer.getType()) && path.equals(httpProducer.getPath());
        }

        return false;
    }

    @Override
    public void visit(AbstractCommunicationModelVisitor visitor) {
        visitor.visit(this);
    }
}
