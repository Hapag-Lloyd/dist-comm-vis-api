package com.hlag.tools.commvis.analyzer.model;

import com.google.gson.annotations.SerializedName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.assertj.core.api.Assertions.assertThat;

class HttpProducerTest {
    @Test
    void shouldHaveSerializedNameAnnotationOnFiled_toDecoupleTheFieldNameFromJson() {
        Field[] declaredFields = CommunicationModel.class.getDeclaredFields();

        for (Field f : declaredFields) {
            SerializedName actualAnnotation = f.getAnnotation(SerializedName.class);

            assertThat(actualAnnotation).isNotNull();
        }
    }
}