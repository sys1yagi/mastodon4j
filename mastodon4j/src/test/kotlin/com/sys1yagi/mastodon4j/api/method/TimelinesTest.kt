package com.sys1yagi.mastodon4j.api.method

import com.sys1yagi.mastodon4j.api.exception.Mastodon4jRequestException
import com.sys1yagi.mastodon4j.testtool.MockClient
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Assert
import kotlin.test.Test
import kotlin.test.Ignore
import kotlin.test.assertFailsWith

class TimelinesTest {

    @Test 
    fun getHome() {
        val client = MockClient.mock("timelines.json")
        val timelines = Timelines(client)
        val pageable = timelines.getHome().execute()
        val status = pageable.part.first()
        status.id shouldBeEqualTo 11111L
    }

    @Test 
    fun homeWithException() {
        assertFailsWith<Mastodon4jRequestException>{
            val client = MockClient.ioException()
            val timelines = Timelines(client)
            timelines.getHome().execute()
        }
    }

    // TODO 401

}
