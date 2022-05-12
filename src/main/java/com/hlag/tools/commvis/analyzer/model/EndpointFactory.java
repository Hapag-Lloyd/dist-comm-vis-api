package com.hlag.tools.commvis.analyzer.model;

public class EndpointFactory {
    private static final EndpointFactory singleton = new EndpointFactory();
    private Long numberGenerator = 0L;

    public static EndpointFactory get() {
        return singleton;
    }

    private synchronized Long getNewIdNumber() {
        return ++numberGenerator;
    }

    public HttpConsumer createHttpConsumer(String className, String methodName, String type, String path) {
        return new HttpConsumer(className, methodName, type, path, getNewIdNumber());
    }

    public HttpProducer createHttpProducer(String className, String methodName, String type, String path, String destinationProjectId) {
        return new HttpProducer(className, methodName, type, path, destinationProjectId, getNewIdNumber());
    }

    public JmsReceiver createJmsReceiver(String className, String destinationType, String destination) {
        return new JmsReceiver(className, destinationType, destination, getNewIdNumber());
    }
}
