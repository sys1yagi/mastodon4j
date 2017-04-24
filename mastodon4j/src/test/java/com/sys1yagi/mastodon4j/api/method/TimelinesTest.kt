package com.sys1yagi.mastodon4j.api.method

import com.sys1yagi.mastodon4j.testtool.MockClient
import org.amshove.kluent.shouldEqualTo
import org.junit.Test

class TimelinesTest {
    @Test
    fun public() {
        val client = MockClient.mock("public_timeline.json")
        val timelines = Timelines(client)
        val statuses = timelines.getPublic()
        statuses.size shouldEqualTo 20
    }

    @Test
    fun tag() {
        val client = MockClient.mock("tag.json")
        val timelines = Timelines(client)
        val statuses = timelines.getTag("mastodon")
        statuses.size shouldEqualTo 20
    }
}
