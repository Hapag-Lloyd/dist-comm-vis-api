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
    String id;

    /**
     *
     * @param producer the producer tested
     * @return {@code true} if this consumer is receives messages from the {@code producer}.
     */
    public boolean isProducedBy(HttpProducer producer) {
        return type.equals(producer.getType()) && path.equals(producer.getPath());
    }

    @Override
    public void visit(AbstractCommunicationModelVisitor visitor) {
        visitor.visit(this);
    }
}
