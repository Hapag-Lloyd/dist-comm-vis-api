package com.hlag.tools.commvis.analyzer.model;

import com.google.gson.annotations.SerializedName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.assertj.core.api.Assertions.assertThat;

class SqsProducerTest {
    @Test
    void shouldHaveSerializedNameAnnotationOnFiled_toDecoupleTheFieldNameFromJson() {
        Field[] declaredFields = SqsProducer.class.getDeclaredFields();

        for (Field f : declaredFields) {
            SerializedName actualAnnotation = f.getAnnotation(SerializedName.class);

            assertThat(actualAnnotation).withFailMessage(() -> String.format("Field %s has no @SerializedName annotation.", f.getName())).isNotNull();
        }
    }
}