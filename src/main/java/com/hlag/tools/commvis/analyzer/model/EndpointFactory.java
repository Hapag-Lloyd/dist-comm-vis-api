package com.hlag.tools.commvis.analyzer.model;

import com.hlag.tools.commvis.analyzer.annotation.*;
import com.hlag.tools.commvis.analyzer.port.IIdentityGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
@RequiredArgsConstructor
public class EndpointFactory {
    private final IIdentityGenerator identityGenerator;

    public HttpConsumer createHttpConsumer(String className, String methodName, String type, String path) {
        return new HttpConsumer(className, methodName, type, path, identityGenerator.generateUniqueId());
    }

    @Deprecated
    public HttpProducer createHttpProducer(String className, String methodName, String type, String path, String destinationProjectId) {
        return new HttpProducer(className, methodName, type, path, destinationProjectId, identityGenerator.generateUniqueId());
    }

    public HttpProducer createHttpProducer(VisualizeHttpsCall annotation, Method method) {
        return new HttpProducer(method.getDeclaringClass().getCanonicalName(), method.getName(), annotation.type(), annotation.path(), annotation.projectId(), identityGenerator.generateUniqueId());
    }

    public JmsReceiver createJmsReceiver(String className, String destinationType, String destination) {
        return new JmsReceiver(className, destinationType, destination, identityGenerator.generateUniqueId());
    }

    @Deprecated
    public SqsConsumer createSqsReceiver(String className, String methodName, String queueName) {
        return new SqsConsumer(className, methodName, queueName, identityGenerator.generateUniqueId());
    }

    public SqsConsumer createSqsConsumer(VisualizeSqsConsumer annotation, Method method) {
        return new SqsConsumer(method.getDeclaringClass().getCanonicalName(), method.getName(), annotation.queueName(), identityGenerator.generateUniqueId());
    }

    public SqsViaSnsConsumer createSqsViaSnsConsumer(VisualizeSqsViaSnsConsumer annotation, Method method) {
        return new SqsViaSnsConsumer(method.getDeclaringClass().getCanonicalName(), method.getName(), annotation.topicName(), identityGenerator.generateUniqueId());
    }

    @Deprecated
    public SqsProducer createSqsProducer(String className, String methodName, String queueName, String destinationProjectId) {
        return new SqsProducer(className, methodName, queueName, destinationProjectId, identityGenerator.generateUniqueId());
    }

    public SqsProducer createSqsProducer(VisualizeSqsProducer annotation, Method method) {
        return new SqsProducer(method.getDeclaringClass().getCanonicalName(), method.getName(), annotation.queueName(), annotation.projectId(), identityGenerator.generateUniqueId());
    }

    public SnsProducer createSnsProducer(VisualizeSnsProducer annotation, Method method) {
        return new SnsProducer(method.getDeclaringClass().getCanonicalName(), method.getName(), annotation.topicName(), annotation.projectId(), identityGenerator.generateUniqueId());
    }

    public KafkaProducer createKafkaProducer(VisualizeKafkaProducer annotation, Method method) {
        return new KafkaProducer(method.getDeclaringClass().getCanonicalName(), method.getName(), annotation.topicName(), annotation.projectId(), identityGenerator.generateUniqueId());
    }

    public KafkaConsumer createKafkaConsumer(VisualizeKafkaConsumer annotation, Method method) {
        return new KafkaConsumer(method.getDeclaringClass().getCanonicalName(), method.getName(), annotation.topicName(), identityGenerator.generateUniqueId());
    }
}
