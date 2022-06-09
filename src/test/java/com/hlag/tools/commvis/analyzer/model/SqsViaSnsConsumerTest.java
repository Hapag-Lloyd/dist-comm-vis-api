package com.hlag.tools.commvis.analyzer.model;

import com.google.gson.annotations.SerializedName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.assertj.core.api.Assertions.assertThat;

class SqsViaSnsConsumerTest {
    private SqsViaSnsConsumer sqsViaSnsConsumer;

    @BeforeEach
    void init() {
        sqsViaSnsConsumer = new SqsViaSnsConsumer("class", "method", "topic", "id");
    }
    @Test
    void shouldHaveSerializedNameAnnotationOnFiled_toDecoupleTheFieldNameFromJson() {
        Field[] declaredFields = SqsViaSnsConsumer.class.getDeclaredFields();

        for (Field f : declaredFields) {
            SerializedName actualAnnotation = f.getAnnotation(SerializedName.class);

            assertThat(actualAnnotation).withFailMessage(() -> String.format("Field %s has no @SerializedName annotation.", f.getName())).isNotNull();
        }
    }

    @Test
    void shouldReturnTrue_whenIsProducedBy_givenProducerIsForSameQueue() {
        SnsProducer givenProducer = new SnsProducer("class1", "method1", "topic", "destinationProject", "id1");

        boolean actualIsProducedBy = sqsViaSnsConsumer.isProducedBy(givenProducer);

        assertThat(actualIsProducedBy).isTrue();
    }

    @Test
    void shouldReturnFalse_whenIsProducedBy_givenProducerIsForOtherQueue() {
        SnsProducer givenProducer = new SnsProducer("class1", "method1", "topic-other", "destinationProject", "id1");

        boolean actualIsProducedBy = sqsViaSnsConsumer.isProducedBy(givenProducer);

        assertThat(actualIsProducedBy).isFalse();
    }
}