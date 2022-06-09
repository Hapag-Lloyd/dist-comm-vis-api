package com.hlag.tools.commvis.analyzer.model;

import com.hlag.tools.commvis.analyzer.annotation.VisualizeSnsProducer;
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

    public HttpProducer createHttpProducer(String className, String methodName, String type, String path, String destinationProjectId) {
        return new HttpProducer(className, methodName, type, path, destinationProjectId, identityGenerator.generateUniqueId());
    }

    public JmsReceiver createJmsReceiver(String className, String destinationType, String destination) {
        return new JmsReceiver(className, destinationType, destination, identityGenerator.generateUniqueId());
    }

    public SqsConsumer createSqsReceiver(String className, String methodName, String queueName) {
        return new SqsConsumer(className, methodName, queueName, identityGenerator.generateUniqueId());
    }

    public SqsProducer createSqsProducer(String className, String methodName, String queueName, String destinationProjectId) {
        return new SqsProducer(className, methodName, queueName, destinationProjectId, identityGenerator.generateUniqueId());
    }

    public SnsProducer createSnsProducer(VisualizeSnsProducer annotation, Method method) {
        return new SnsProducer(method.getDeclaringClass().getCanonicalName(), method.getName(), annotation.topicName(), annotation.projectId(), identityGenerator.generateUniqueId());
    }
}
