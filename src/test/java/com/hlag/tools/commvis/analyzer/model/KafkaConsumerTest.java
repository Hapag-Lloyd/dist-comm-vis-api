package com.hlag.tools.commvis.analyzer.model;

import com.google.gson.annotations.SerializedName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.assertj.core.api.Assertions.assertThat;

class KafkaConsumerTest {
    private KafkaConsumer kafkaConsumer;

    @BeforeEach
    void init() {
        kafkaConsumer = new KafkaConsumer("class", "method", "queue", "id");
    }
    @Test
    void shouldHaveSerializedNameAnnotationOnFiled_toDecoupleTheFieldNameFromJson() {
        Field[] declaredFields = KafkaConsumer.class.getDeclaredFields();

        for (Field f : declaredFields) {
            SerializedName actualAnnotation = f.getAnnotation(SerializedName.class);

            assertThat(actualAnnotation).withFailMessage(() -> String.format("Field %s has no @SerializedName annotation.", f.getName())).isNotNull();
        }
    }

    @Test
    void shouldReturnTrue_whenIsProducedBy_givenProducerIsForSameTopic() {
        KafkaProducer givenProducer = new KafkaProducer("class1", "method1", "topic", "destinationProject", "id1");

        boolean actualIsProducedBy = kafkaConsumer.isProducedBy(givenProducer);

        assertThat(actualIsProducedBy).isTrue();
    }

    @Test
    void shouldReturnFalse_whenIsProducedBy_givenProducerIsForOtherTopic() {
        KafkaProducer givenProducer = new KafkaProducer("class1", "method1", "topic-other", "destinationProject", "id1");

        boolean actualIsProducedBy = kafkaConsumer.isProducedBy(givenProducer);

        assertThat(actualIsProducedBy).isFalse();
    }
}