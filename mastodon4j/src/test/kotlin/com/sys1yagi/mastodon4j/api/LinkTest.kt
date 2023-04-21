package com.sys1yagi.mastodon4j.api

import org.amshove.kluent.shouldBeEqualTo
import kotlin.test.Test

import org.junit.Assert.*

class LinkTest {
    @Test
    fun parse() {
        // both
        run {
            val link = Link.parse(
                    """
<https://mstdn.jp/api/v1/timelines/public?limit=20&local=true&max_id=1552>; rel="next", <https://mstdn.jp/api/v1/timelines/public?limit=20&local=true&since_id=105>; rel="prev"
"""
            )
            if (link == null) {
                fail()
                return
            }
            link.maxId shouldBeEqualTo 1552
            link.sinceId shouldBeEqualTo 105
        }

        // max
        run {
            val link = Link.parse(
                    """
<https://mstdn.jp/api/v1/timelines/public?limit=20&local=true&max_id=1553>; rel="next"
"""
            )
            if (link == null) {
                fail()
                return
            }
            link.maxId shouldBeEqualTo 1553
            link.sinceId shouldBeEqualTo 0
            link.prevPath shouldBeEqualTo ""
        }

        // since
        run {
            val link = Link.parse(
                    """
<https://mstdn.jp/api/v1/timelines/public?limit=20&local=true&since_id=105>; rel="prev"
"""
            )
            if (link == null) {
                fail()
                return
            }
            link.maxId shouldBeEqualTo 0
            link.nextPath shouldBeEqualTo ""
            link.sinceId shouldBeEqualTo 105
        }
    }
}