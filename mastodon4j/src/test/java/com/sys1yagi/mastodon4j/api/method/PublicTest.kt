package com.sys1yagi.mastodon4j.api.method

import com.sys1yagi.mastodon4j.testtool.MockClient
import org.amshove.kluent.shouldEqualTo
import org.junit.Test

class PublicTest {
    @Test
    fun getInstance() {
        // TODO
    }

    @Test
    fun getSearch() {
        val client = MockClient.mock("search.json")

        val publicMethod = Public(client)
        val result = publicMethod.getSearch("test")
        result.statuses.size shouldEqualTo 0
        result.accounts.size shouldEqualTo 6
        result.hashtags.size shouldEqualTo 5
        result.hashtags.size shouldEqualTo 5
    }
}
