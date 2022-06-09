package com.hlag.tools.commvis.analyzer.model;

import com.google.gson.annotations.SerializedName;
import lombok.*;

import java.util.UUID;

/**
 * A receiver for JMS messages.
 */
@Value
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class JmsReceiver implements ISenderReceiverCommunication, IConsumer {
    @SerializedName(value="class_name")
    String className;

    // e.g. "javax.jms.Queue"
    @SerializedName(value="destination_type")
    String destinationType;

    // e.g. "jms/catalogs/customer"
    @SerializedName(value="destination")
    String destination;

    @SerializedName(value="id")
    String id;

    @Override
    public void visit(AbstractCommunicationModelVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public boolean isProducedBy(IProducer producer) {
        // we have no producers so far
        return false;
    }
}
