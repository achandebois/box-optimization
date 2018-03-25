package com.achandebois.service;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FactoryTest {

    @Test
    void should_optimize_throwsException_whenInvalidInput() {
        //Given
        Factory factory = new Factory();

        //Then
        assertThrows(IllegalArgumentException.class, () -> factory.optimize("darzarzar"));
    }

    @Test
    void should_optimize_fillBoxes() {
        //Given
        Factory factory = new Factory();

        //When
        factory.optimize("163841689525773");

        //Then
        assertThat(factory.countBoxes()).isEqualTo(8);
    }
}
