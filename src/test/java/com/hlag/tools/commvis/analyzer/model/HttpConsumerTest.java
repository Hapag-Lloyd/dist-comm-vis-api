package com.hlag.tools.commvis.analyzer.model;

import com.google.gson.annotations.SerializedName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.assertj.core.api.Assertions.assertThat;

class HttpConsumerTest {
    private HttpConsumer consumer;
    private final static String CLASS_NAME = "className";
    private final static String METHOD_NAME = "methodName";
    private final static String TYPE = "type";
    private final static String PATH = "path";
    private final static String ID = "id";

    @BeforeEach
    void init() {
        consumer = new HttpConsumer(CLASS_NAME, METHOD_NAME, TYPE, PATH, ID);
    }

    @Test
    void shouldHaveSerializedNameAnnotationOnFiled_toDecoupleTheFieldNameFromJson() {
        Field[] declaredFields = CommunicationModel.class.getDeclaredFields();

        for (Field f : declaredFields) {
            SerializedName actualAnnotation = f.getAnnotation(SerializedName.class);

            assertThat(actualAnnotation).isNotNull();
        }
    }

    @Test
    void shouldIdentifyMatchingConsumerAndProducer_whenIsProducedBy() {
        HttpProducer givenProducer = new HttpProducer("x", "y", TYPE, PATH, "destinationId", "id1");

        boolean actualMatching = consumer.isProducedBy(givenProducer);

        assertThat(actualMatching).isTrue();
    }

    @Test
    void shouldNotIdentifyMatchingConsumerAndProducer_whenIsProducedBy_givenPathIsDifferent() {
        HttpProducer givenProducer = new HttpProducer("x", "y", TYPE, "my-path", "destinationId", "id1");

        boolean actualMatching = consumer.isProducedBy(givenProducer);

        assertThat(actualMatching).isFalse();
    }

    @Test
    void shouldIdentifyMatchingConsumerAndProducer_whenIsProducedBy_givenTypeIsDifferent() {
        HttpProducer givenProducer = new HttpProducer("x", "y", "my-type", PATH, "destinationId", "id1");

        boolean actualMatching = consumer.isProducedBy(givenProducer);

        assertThat(actualMatching).isFalse();
    }
}