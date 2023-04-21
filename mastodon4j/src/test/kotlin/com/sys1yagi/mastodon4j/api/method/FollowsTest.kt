package com.sys1yagi.mastodon4j.api.method

import com.sys1yagi.mastodon4j.api.exception.Mastodon4jRequestException
import com.sys1yagi.mastodon4j.testtool.MockClient
import org.amshove.kluent.shouldBeEqualTo
import kotlin.test.Test
import kotlin.test.Ignore

import kotlin.test.assertFailsWith

import org.junit.Assert.*

class FollowsTest {
    @Test 
    fun postRemoteFollow() {
        val client = MockClient.mock("follow.json")

        val follows = Follows(client)
        val account = follows.postRemoteFollow("test").execute()
        account.acct shouldBeEqualTo "test@test.com"
        account.displayName shouldBeEqualTo "test"
        account.userName shouldBeEqualTo "test"
    }

    @Test 
    fun postRemoteFollowWithException() {
        assertFailsWith<Mastodon4jRequestException>{
            val client = MockClient.ioException()
            val follows = Follows(client)
            follows.postRemoteFollow("test").execute()
        }
    }

}
