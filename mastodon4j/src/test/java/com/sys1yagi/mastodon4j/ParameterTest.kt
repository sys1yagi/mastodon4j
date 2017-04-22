package com.sys1yagi.mastodon4j

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class ParameterTest {
    @Test
    fun parameterEmpty() {
        assertThat(Parameter().build()).isEmpty()
    }

    @Test
    fun parameterOne() {
        assertThat(
                Parameter()
                        .append("test", "value")
                        .build()
        ).isEqualTo("test=value")
    }

    @Test
    fun parametersOne() {
        assertThat(
                Parameter()
                        .append("test", "value")
                        .append("id", 3L)
                        .append("max", 10)
                        .append("is_staff", false)
                        .build()
        ).isEqualTo("test=value&id=3&max=10&is_staff=false")
    }
}
