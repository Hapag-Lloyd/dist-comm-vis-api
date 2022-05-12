package com.hlag.tools.commvis.analyzer.model;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * A receiver for JMS messages.
 */
@AllArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class JmsReceiver implements ISenderReceiverCommunication {
    @SerializedName(value="class_name")
    private String className;

    // e.g. "javax.jms.Queue"
    @SerializedName(value="destination_type")
    private String destinationType;

    // e.g. "jms/catalogs/customer"
    @SerializedName(value="destination")
    private String destination;

    @Override
    public void visit(AbstractCommunicationModelVisitor visitor) {
        visitor.visit(this);
    }
}
