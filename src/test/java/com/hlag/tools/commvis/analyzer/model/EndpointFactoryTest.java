package com.hlag.tools.commvis.analyzer.model;

import com.hlag.tools.commvis.analyzer.port.IIdentityGenerator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class EndpointFactoryTest {
    private static final String FIXED_ID = "MY-UNIQUE-ID";

    @Mock
    private IIdentityGenerator identityGenerator;
    @InjectMocks
    private EndpointFactory factory;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);

        Mockito.doReturn(FIXED_ID).when(identityGenerator).generateUniqueId();
    }

    @Test
    void shouldSetAllFields_whenCreateHttpConsumer() {
        HttpConsumer actualHttpConsumer = factory.createHttpConsumer("className", "methodName", "type", "path");

        Assertions.assertThat(actualHttpConsumer.getClassName()).isEqualTo("className");
        Assertions.assertThat(actualHttpConsumer.getMethodName()).isEqualTo("methodName");
        Assertions.assertThat(actualHttpConsumer.getType()).isEqualTo("type");
        Assertions.assertThat(actualHttpConsumer.getPath()).isEqualTo("path");
        Assertions.assertThat(actualHttpConsumer.getId()).isEqualTo(FIXED_ID);
    }

    @Test
    void shouldSetAllFields_whenCreateHttpProducer() {
        HttpProducer actualHttpProducer = factory.createHttpProducer("className", "methodName", "type", "path", "destinationProjectId");

        Assertions.assertThat(actualHttpProducer.getClassName()).isEqualTo("className");
        Assertions.assertThat(actualHttpProducer.getMethodName()).isEqualTo("methodName");
        Assertions.assertThat(actualHttpProducer.getType()).isEqualTo("type");
        Assertions.assertThat(actualHttpProducer.getPath()).isEqualTo("path");
        Assertions.assertThat(actualHttpProducer.getDestinationProjectId()).isEqualTo("destinationProjectId");
        Assertions.assertThat(actualHttpProducer.getId()).isEqualTo(FIXED_ID);

    }

    @Test
    void shouldSetAllFields_whenCreateJmsReceiver() {
        JmsReceiver actualJmsReceiver = factory.createJmsReceiver("className", "destinationType", "destination");

        Assertions.assertThat(actualJmsReceiver.getClassName()).isEqualTo("className");
        Assertions.assertThat(actualJmsReceiver.getDestinationType()).isEqualTo("destinationType");
        Assertions.assertThat(actualJmsReceiver.getDestination()).isEqualTo("destination");
        Assertions.assertThat(actualJmsReceiver.getId()).isEqualTo(FIXED_ID);
    }

    @Test
    void shouldSetAllFields_whenCreateSqsReceiver() {
        SqsConsumer actualSqsConsumer = factory.createSqsReceiver("className", "methodName", "queueName");

        Assertions.assertThat(actualSqsConsumer.getClassName()).isEqualTo("className");
        Assertions.assertThat(actualSqsConsumer.getMethodName()).isEqualTo("methodName");
        Assertions.assertThat(actualSqsConsumer.getQueueName()).isEqualTo("queueName");
        Assertions.assertThat(actualSqsConsumer.getId()).isEqualTo(FIXED_ID);
    }

}