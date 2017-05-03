package com.sys1yagi.mastodon4j.api.method

import com.sys1yagi.mastodon4j.api.exception.Mastodon4jRequestException
import com.sys1yagi.mastodon4j.testtool.MockClient
import org.amshove.kluent.shouldEqualTo
import org.junit.Test

class FollowRequestsTest {
    @Test
    fun getFollowRequests() {
        val client = MockClient.mock("follow_requests.json")

        val followRequests = FollowRequests(client)
        val pageable = followRequests.getFollowRequests().execute()
        val account = pageable.part.first()
        account.acct shouldEqualTo "test@test.com"
        account.displayName shouldEqualTo "test"
        account.userName shouldEqualTo "test"
    }

    @Test(expected = Mastodon4jRequestException::class)
    fun getFollowRequestsWithException() {
        val client = MockClient.ioException()
        val followRequests = FollowRequests(client)
        followRequests.getFollowRequests().execute()
    }
}