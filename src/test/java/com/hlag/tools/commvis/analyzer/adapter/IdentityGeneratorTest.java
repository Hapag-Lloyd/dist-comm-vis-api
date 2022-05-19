package com.hlag.tools.commvis.analyzer.adapter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class IdentityGeneratorTest {
    private IdentityGenerator identityGenerator;

    @BeforeEach
    void init() {
        identityGenerator = new IdentityGenerator();
    }

    @Test
    void shouldGenerateUniqueIds_whenGenerateUniqueId() {
        List<String> actualUniqueIds = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            actualUniqueIds.add(identityGenerator.generateUniqueId());
        }

        Assertions.assertThat(actualUniqueIds).doesNotHaveDuplicates();
    }
}