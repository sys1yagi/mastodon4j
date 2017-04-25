package com.sys1yagi.mastodon4j.api.method

import com.sys1yagi.mastodon4j.testtool.MockClient
import org.amshove.kluent.shouldEqualTo
import org.junit.Test

class TimelinesTest {
    @Test
    fun public() {
        val client = MockClient.mock("public_timeline.json", maxId = 3L, sinceId = 1L)
        val timelines = Timelines(client)
        val statuses = timelines.getLocalPublic()
        statuses.part.size shouldEqualTo 20
        statuses.link.nextPath shouldEqualTo "<https://mstdn.jp/api/v1/timelines/public?limit=20&local=true&max_id=3>"
        statuses.link.maxId shouldEqualTo 3L
        statuses.link.prevPath shouldEqualTo "<https://mstdn.jp/api/v1/timelines/public?limit=20&local=true&since_id=1>"
        statuses.link.sinceId shouldEqualTo 1L
    }

    @Test
    fun tag() {
        val client = MockClient.mock("tag.json", maxId = 3L, sinceId = 1L)
        val timelines = Timelines(client)
        val statuses = timelines.getLocalTag("mastodon")
        statuses.part.size shouldEqualTo 20
    }
}
