package com.hlag.tools.commvis.analyzer.model;

/**
 * Indentifies an {@link ISenderReceiverCommunication} as a consumer of something.
 */
public interface IConsumer {
    /**
     *
     * @param producer the producer tested
     * @return {@code true} if this consumer receives messages from the {@code producer}.
     */
    boolean isProducedBy(IProducer producer);
}
