package com.sys1yagi.mastodon4j.api

import org.amshove.kluent.shouldBeEqualTo
import kotlin.test.Test

class RangeTest {
    @Test
    fun toParameter() {
        run {
            val range = Range()
            range.toParameter().build() shouldBeEqualTo "limit=20"
        }

        run {
            val range = Range(maxId = 10)
            range.toParameter().build() shouldBeEqualTo "max_id=10&limit=20"
        }

        run {
            val range = Range(maxId = 5, sinceId = 3, limit = 25)
            range.toParameter().build() shouldBeEqualTo "max_id=5&since_id=3&limit=25"
        }
    }
}