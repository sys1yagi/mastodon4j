package com.sys1yagi.mastodon4j

import org.amshove.kluent.shouldBeEqualTo
import kotlin.test.Test

class ParameterTest {
    @Test
    fun parameterEmpty() {
        Parameter().build() shouldBeEqualTo ""
    }

    @Test
    fun parameterOne() {
        Parameter()
                .append("test", "value")
                .build() shouldBeEqualTo "test=value"
    }

    @Test
    fun parametersOne() {
        Parameter()
                .append("test", "value")
                .append("id", 3L)
                .append("max", 10)
                .append("is_staff", false)
                .build() shouldBeEqualTo "test=value&id=3&max=10&is_staff=false"
    }

    @Test
    fun parameterArray() {
        Parameter()
                .append("media_ids", listOf(1, 3, 4))
                .build() shouldBeEqualTo "media_ids[]=1&media_ids[]=3&media_ids[]=4"
    }
}
