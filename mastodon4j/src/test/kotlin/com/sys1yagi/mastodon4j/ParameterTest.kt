package com.sys1yagi.mastodon4j

import org.amshove.kluent.shouldEqualTo
import org.junit.Test

class ParameterTest {
    @Test
    fun parameterEmpty() {
        Parameter().build() shouldEqualTo ""
    }

    @Test
    fun parameterOne() {
        Parameter()
                .append("test", "value")
                .build() shouldEqualTo "test=value"
    }

    @Test
    fun parametersOne() {
        Parameter()
                .append("test", "value")
                .append("id", 3L)
                .append("max", 10)
                .append("is_staff", false)
                .build() shouldEqualTo "test=value&id=3&max=10&is_staff=false"
    }

    @Test
    fun parameterArray() {
        Parameter()
                .append("media_ids", listOf(1, 3, 4))
                .build() shouldEqualTo "media_ids[]=1&media_ids[]=3&media_ids[]=4"
    }
}
