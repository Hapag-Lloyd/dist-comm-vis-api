package com.hlag.tools.commvis.analyzer.model;

import com.google.gson.annotations.SerializedName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import static org.assertj.core.api.Assertions.assertThat;

class CommunicationModelTest {
    @Test
    void shouldHaveSerializedNameAnnotationOnFiled_toDecoupleTheFieldNameFromJson() {
        Field[] declaredFields = CommunicationModel.class.getDeclaredFields();

        for (Field f : declaredFields) {
            if (! Modifier.isStatic(f.getModifiers())) {
                SerializedName actualAnnotation = f.getAnnotation(SerializedName.class);

                assertThat(actualAnnotation).withFailMessage(() -> String.format("Field %s has no @SerializedName annotation.", f.getName())).isNotNull();
            }
        }
    }
}