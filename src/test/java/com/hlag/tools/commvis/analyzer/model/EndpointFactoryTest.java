package com.hlag.tools.commvis.analyzer.model;

import com.hlag.tools.commvis.analyzer.annotation.*;
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

    private static class TestProducersAndConsumers {
        @VisualizeSnsProducer(topicName = "topic", projectId = "4711")
        public void produceSnsMessage() {}

        @VisualizeSqsViaSnsConsumer(topicName = "topic1", projectName = "4712")
        public void consumeSqsViaSnsMessage() {}

        @VisualizeKafkaConsumer(topicName = "topic2", projectName = "4713")
        public void consumeKafkaMessage() {}

        @VisualizeKafkaProducer(topicName = "topic3", projectId = "4714")
        public void produceKafkaMessage() {}

        @VisualizeSqsProducer(queueName = "queue4", projectId = "4715")
        public void produceSqsMessage() {}

        @VisualizeSqsConsumer(queueName = "queue5", projectName = "4716")
        public void consumeSqsMessage() {}

        @VisualizeHttpsCall(type = "GET", path = "a/b/c", projectId = "4717")
        public void produceHttpsMessage() {}
    }

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

    @Test
    void shouldSetAllFields_whenCreateSnsProducer() throws NoSuchMethodException {
        SnsProducer actualSnsProducer = factory.createSnsProducer(TestProducersAndConsumers.class.getDeclaredMethod("produceSnsMessage").getAnnotationsByType(VisualizeSnsProducer.class)[0], TestProducersAndConsumers.class.getDeclaredMethod("produceSnsMessage"));

        Assertions.assertThat(actualSnsProducer.getClassName()).isEqualTo("com.hlag.tools.commvis.analyzer.model.EndpointFactoryTest.TestProducersAndConsumers");
        Assertions.assertThat(actualSnsProducer.getMethodName()).isEqualTo("produceSnsMessage");
        Assertions.assertThat(actualSnsProducer.getTopicName()).isEqualTo("topic");
        Assertions.assertThat(actualSnsProducer.getDestinationProjectId()).isEqualTo("4711");
        Assertions.assertThat(actualSnsProducer.getId()).isEqualTo(FIXED_ID);
    }

    @Test
    void shouldSetAllFields_whenCreateSqsViaSnsConsumer() throws NoSuchMethodException {
        SqsViaSnsConsumer actualSqsViaSnsConsumer = factory.createSqsViaSnsConsumer(TestProducersAndConsumers.class.getDeclaredMethod("consumeSqsViaSnsMessage").getAnnotationsByType(VisualizeSqsViaSnsConsumer.class)[0], TestProducersAndConsumers.class.getDeclaredMethod("consumeSqsViaSnsMessage"));

        Assertions.assertThat(actualSqsViaSnsConsumer.getClassName()).isEqualTo("com.hlag.tools.commvis.analyzer.model.EndpointFactoryTest.TestProducersAndConsumers");
        Assertions.assertThat(actualSqsViaSnsConsumer.getMethodName()).isEqualTo("consumeSqsViaSnsMessage");
        Assertions.assertThat(actualSqsViaSnsConsumer.getTopicName()).isEqualTo("topic1");
        Assertions.assertThat(actualSqsViaSnsConsumer.getId()).isEqualTo(FIXED_ID);
    }

    @Test
    void shouldSetAllFields_whenCreateKafkaConsumer() throws NoSuchMethodException {
        KafkaConsumer actualKafkaConsumer = factory.createKafkaConsumer(TestProducersAndConsumers.class.getDeclaredMethod("consumeKafkaMessage").getAnnotationsByType(VisualizeKafkaConsumer.class)[0], TestProducersAndConsumers.class.getDeclaredMethod("consumeKafkaMessage"));

        Assertions.assertThat(actualKafkaConsumer.getClassName()).isEqualTo("com.hlag.tools.commvis.analyzer.model.EndpointFactoryTest.TestProducersAndConsumers");
        Assertions.assertThat(actualKafkaConsumer.getMethodName()).isEqualTo("consumeKafkaMessage");
        Assertions.assertThat(actualKafkaConsumer.getTopicName()).isEqualTo("topic2");
        Assertions.assertThat(actualKafkaConsumer.getId()).isEqualTo(FIXED_ID);
    }

    @Test
    void shouldSetAllFields_whenCreateKafkaProducer() throws NoSuchMethodException {
        KafkaProducer actualKafkaProducer = factory.createKafkaProducer(TestProducersAndConsumers.class.getDeclaredMethod("produceKafkaMessage").getAnnotationsByType(VisualizeKafkaProducer.class)[0], TestProducersAndConsumers.class.getDeclaredMethod("produceKafkaMessage"));

        Assertions.assertThat(actualKafkaProducer.getClassName()).isEqualTo("com.hlag.tools.commvis.analyzer.model.EndpointFactoryTest.TestProducersAndConsumers");
        Assertions.assertThat(actualKafkaProducer.getMethodName()).isEqualTo("produceKafkaMessage");
        Assertions.assertThat(actualKafkaProducer.getTopicName()).isEqualTo("topic3");
        Assertions.assertThat(actualKafkaProducer.getDestinationProjectId()).isEqualTo("4714");
        Assertions.assertThat(actualKafkaProducer.getId()).isEqualTo(FIXED_ID);
    }


    @Test
    void shouldSetAllFields_whenCreateSqsProducer() throws NoSuchMethodException {
        SqsProducer actualSqsProducer = factory.createSqsProducer(TestProducersAndConsumers.class.getDeclaredMethod("produceSqsMessage").getAnnotationsByType(VisualizeSqsProducer.class)[0], TestProducersAndConsumers.class.getDeclaredMethod("produceSqsMessage"));

        Assertions.assertThat(actualSqsProducer.getClassName()).isEqualTo("com.hlag.tools.commvis.analyzer.model.EndpointFactoryTest.TestProducersAndConsumers");
        Assertions.assertThat(actualSqsProducer.getMethodName()).isEqualTo("produceSqsMessage");
        Assertions.assertThat(actualSqsProducer.getQueueName()).isEqualTo("queue4");
        Assertions.assertThat(actualSqsProducer.getDestinationProjectId()).isEqualTo("4715");
        Assertions.assertThat(actualSqsProducer.getId()).isEqualTo(FIXED_ID);
    }

    @Test
    void shouldSetAllFields_whenCreateSqsConsumer() throws NoSuchMethodException {
        SqsConsumer actualSqsConsumer = factory.createSqsConsumer(TestProducersAndConsumers.class.getDeclaredMethod("consumeSqsMessage").getAnnotationsByType(VisualizeSqsConsumer.class)[0], TestProducersAndConsumers.class.getDeclaredMethod("consumeSqsMessage"));

        Assertions.assertThat(actualSqsConsumer.getClassName()).isEqualTo("com.hlag.tools.commvis.analyzer.model.EndpointFactoryTest.TestProducersAndConsumers");
        Assertions.assertThat(actualSqsConsumer.getMethodName()).isEqualTo("consumeSqsMessage");
        Assertions.assertThat(actualSqsConsumer.getQueueName()).isEqualTo("queue5");
        Assertions.assertThat(actualSqsConsumer.getId()).isEqualTo(FIXED_ID);
    }

    @Test
    void shouldSetAllFields_whenCreateHttpProducer_givenAnnotationAndMethod() throws NoSuchMethodException {
        HttpProducer actualHttpProducer = factory.createHttpProducer(TestProducersAndConsumers.class.getDeclaredMethod("produceHttpsMessage").getAnnotationsByType(VisualizeHttpsCall.class)[0], TestProducersAndConsumers.class.getDeclaredMethod("produceHttpsMessage"));

        Assertions.assertThat(actualHttpProducer.getClassName()).isEqualTo("com.hlag.tools.commvis.analyzer.model.EndpointFactoryTest.TestProducersAndConsumers");
        Assertions.assertThat(actualHttpProducer.getMethodName()).isEqualTo("produceHttpsMessage");
        Assertions.assertThat(actualHttpProducer.getType()).isEqualTo("GET");
        Assertions.assertThat(actualHttpProducer.getPath()).isEqualTo("a/b/c");
        Assertions.assertThat(actualHttpProducer.getDestinationProjectId()).isEqualTo("4717");
        Assertions.assertThat(actualHttpProducer.getId()).isEqualTo(FIXED_ID);
    }
}