package com.sys1yagi.mastodon4j.api.method

import com.sys1yagi.mastodon4j.testtool.MockClient
import org.amshove.kluent.shouldEqualTo
import org.junit.Assert
import org.junit.Test

class TimelinesTest {
    @Test
    fun public() {
        val client = MockClient.mock("public_timeline.json", maxId = 3L, sinceId = 1L)
        val timelines = Timelines(client)
        val statuses = timelines.getLocalPublic()
        statuses.part.size shouldEqualTo 20
        statuses.link?.let {
            it.nextPath shouldEqualTo "<https://mstdn.jp/api/v1/timelines/public?limit=20&local=true&max_id=3>"
            it.maxId shouldEqualTo 3L
            it.prevPath shouldEqualTo "<https://mstdn.jp/api/v1/timelines/public?limit=20&local=true&since_id=1>"
            it.sinceId shouldEqualTo 1L
        }
    }

    @Test
    fun tag() {
        val client = MockClient.mock("tag.json", maxId = 3L, sinceId = 1L)
        val timelines = Timelines(client)
        val statuses = timelines.getLocalTag("mastodon")
        statuses.part.size shouldEqualTo 20
    }
}
