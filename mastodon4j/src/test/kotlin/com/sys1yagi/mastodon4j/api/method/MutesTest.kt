package com.sys1yagi.mastodon4j.api.method

import com.sys1yagi.mastodon4j.api.exception.Mastodon4jRequestException
import com.sys1yagi.mastodon4j.testtool.MockClient
import org.amshove.kluent.shouldBeEqualTo
import kotlin.test.Test
import kotlin.test.Ignore

import kotlin.test.assertFailsWith

import org.junit.Assert.*

class MutesTest {
    @Test 
    fun getMutes() {
        val client = MockClient.mock("mutes.json")
        val mutes = Mutes(client)
        val pageable = mutes.getMutes().execute()
        val account = pageable.part.first()
        account.acct shouldBeEqualTo "test@test.com"
        account.displayName shouldBeEqualTo "test"
        account.userName shouldBeEqualTo "test"
    }

    @Test 
    fun getMutesWithException() {
        assertFailsWith<Mastodon4jRequestException>{
            val client = MockClient.ioException()
            val mutes = Mutes(client)
            mutes.getMutes().execute()
        }
    }
}
