package com.hlag.tools.commvis.analyzer.model;

import com.google.gson.annotations.SerializedName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.assertj.core.api.Assertions.assertThat;

class SqsConsumerTest {
    private SqsConsumer sqsConsumer;

    @BeforeEach
    void init() {
        sqsConsumer = new SqsConsumer("class", "method", "queue", "id");
    }
    @Test
    void shouldHaveSerializedNameAnnotationOnFiled_toDecoupleTheFieldNameFromJson() {
        Field[] declaredFields = SqsConsumer.class.getDeclaredFields();

        for (Field f : declaredFields) {
            SerializedName actualAnnotation = f.getAnnotation(SerializedName.class);

            assertThat(actualAnnotation).withFailMessage(() -> String.format("Field %s has no @SerializedName annotation.", f.getName())).isNotNull();
        }
    }

    @Test
    void shouldReturnTrue_whenIsProducedBy_givenProducerIsForSameQueue() {
        SqsProducer givenProducer = new SqsProducer("class1", "method1", "queue", "destinationProject", "id1");

        boolean actualIsProducedBy = sqsConsumer.isProducedBy(givenProducer);

        assertThat(actualIsProducedBy).isTrue();
    }

    @Test
    void shouldReturnFalse_whenIsProducedBy_givenProducerIsForOtherQueue() {
        SqsProducer givenProducer = new SqsProducer("class1", "method1", "queue-other", "destinationProject", "id1");

        boolean actualIsProducedBy = sqsConsumer.isProducedBy(givenProducer);

        assertThat(actualIsProducedBy).isFalse();
    }
}