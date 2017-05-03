package com.sys1yagi.mastodon4j.api.method

import com.sys1yagi.mastodon4j.api.exception.Mastodon4jRequestException
import com.sys1yagi.mastodon4j.testtool.MockClient
import org.amshove.kluent.shouldEqualTo
import org.junit.Test

import org.junit.Assert.*

class FollowsTest {
    @Test
    fun postRemoteFollow() {
        val client = MockClient.mock("follow.json")

        val follows = Follows(client)
        val account = follows.postRemoteFollow("test").execute()
        account.acct shouldEqualTo "test@test.com"
        account.displayName shouldEqualTo "test"
        account.userName shouldEqualTo "test"
    }

    @Test(expected = Mastodon4jRequestException::class)
    fun postRemoteFollowWithException() {
        val client = MockClient.ioException()
        val follows = Follows(client)
        follows.postRemoteFollow("test").execute()
    }

}