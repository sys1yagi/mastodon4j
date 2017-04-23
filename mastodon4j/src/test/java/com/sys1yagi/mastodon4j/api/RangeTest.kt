package com.sys1yagi.mastodon4j.api

import org.amshove.kluent.shouldEqualTo
import org.junit.Test

class RangeTest {
    @Test
    fun toParameter() {
        run {
            val range = Range()
            range.toParameter().build() shouldEqualTo "limit=20"
        }

        run {
            val range = Range(maxId = 10)
            range.toParameter().build() shouldEqualTo "max_id=10&limit=20"
        }

        run {
            val range = Range(maxId = 5, sinceId = 3, limit = 25)
            range.toParameter().build() shouldEqualTo "max_id=5&since_id=3&limit=25"
        }
    }
}