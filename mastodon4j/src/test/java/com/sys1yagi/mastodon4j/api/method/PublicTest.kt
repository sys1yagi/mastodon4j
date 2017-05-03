package com.sys1yagi.mastodon4j.api.method

import com.sys1yagi.mastodon4j.api.exception.Mastodon4jRequestException
import com.sys1yagi.mastodon4j.testtool.MockClient
import org.amshove.kluent.shouldEqualTo
import org.junit.Test

class PublicTest {
    @Test
    fun getInstance() {
        val client = MockClient.mock("instance.json")
        val publicMethod = Public(client)

        val instance = publicMethod.getInstance()
        instance.uri shouldEqualTo "test.com"
        instance.title shouldEqualTo "test.com"
        instance.description shouldEqualTo "description"
        instance.email shouldEqualTo "owner@test.com"
        instance.version shouldEqualTo "1.3.2"
    }

    @Test(expected = Mastodon4jRequestException::class)
    fun getInstanceWithException() {
        val client = MockClient.ioException()
        val publicMethod = Public(client)
        publicMethod.getInstance()
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

    @Test(expected = Mastodon4jRequestException::class)
    fun getSearchWithException() {
        val client = MockClient.ioException()
        val publicMethod = Public(client)
        publicMethod.getSearch("test")
    }

    @Test
    fun getLocalPublic() {
        val client = MockClient.mock("public_timeline.json", maxId = 3L, sinceId = 1L)
        val publicMethod = Public(client)
        val statuses = publicMethod.getLocalPublic()
        statuses.part.size shouldEqualTo 20
        statuses.link?.let {
            it.nextPath shouldEqualTo "<https://mstdn.jp/api/v1/timelines/public?limit=20&local=true&max_id=3>"
            it.maxId shouldEqualTo 3L
            it.prevPath shouldEqualTo "<https://mstdn.jp/api/v1/timelines/public?limit=20&local=true&since_id=1>"
            it.sinceId shouldEqualTo 1L
        }
    }

    @Test(expected = Mastodon4jRequestException::class)
    fun getLocalPublicWithException() {
        val client = MockClient.ioException()
        val publicMethod = Public(client)
        publicMethod.getLocalPublic()
    }

    @Test
    fun getLocalTag() {
        val client = MockClient.mock("tag.json", maxId = 3L, sinceId = 1L)
        val publicMethod = Public(client)
        val statuses = publicMethod.getLocalTag("mastodon")
        statuses.part.size shouldEqualTo 20
    }

    @Test(expected = Mastodon4jRequestException::class)
    fun getLocalTagWithException() {
        val client = MockClient.ioException()
        val publicMethod = Public(client)
        publicMethod.getLocalTag("mastodon")
    }

}
