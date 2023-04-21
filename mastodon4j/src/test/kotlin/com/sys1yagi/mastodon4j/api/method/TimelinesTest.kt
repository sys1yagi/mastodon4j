package com.sys1yagi.mastodon4j.api.method

import com.sys1yagi.mastodon4j.api.exception.Mastodon4jRequestException
import com.sys1yagi.mastodon4j.testtool.MockClient
import org.amshove.kluent.shouldEqualTo
import org.junit.Assert
import org.junit.Test

class TimelinesTest {

    @Test
    fun getHome() {
        val client = MockClient.mock("timelines.json")
        val timelines = Timelines(client)
        val pageable = timelines.getHome().execute()
        val status = pageable.part.first()
        status.id shouldEqualTo 11111L
    }

    @Test(expected = Mastodon4jRequestException::class)
    fun homeWithException() {
        val client = MockClient.ioException()
        val timelines = Timelines(client)
        timelines.getHome().execute()
    }

    // TODO 401

}
