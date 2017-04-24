package com.sys1yagi.mastodon4j.api.method

import com.sys1yagi.mastodon4j.testtool.MockClient
import org.amshove.kluent.shouldEqualTo
import org.junit.Test

class SearchTest {
    @Test
    fun getSearch() {
        val client = MockClient.mock("search.json")

        val search = Search(client)
        val result = search.getSearch("test")
        result.statuses.size shouldEqualTo 0
        result.accounts.size shouldEqualTo 6
        result.hashtags.size shouldEqualTo 5
        result.hashtags.size shouldEqualTo 5
    }
}
