package com.sys1yagi.mastodon4j.api.method

import com.sys1yagi.mastodon4j.api.exception.Mastodon4jRequestException
import com.sys1yagi.mastodon4j.testtool.MockClient
import org.amshove.kluent.shouldBeEqualTo
import kotlin.test.Test
import kotlin.test.Ignore
import kotlin.test.assertFailsWith

import java.util.concurrent.atomic.AtomicInteger

class PublicTest {
    @Test 
    fun getInstance() {
        val client = MockClient.mock("instance.json")
        val publicMethod = Public(client)

        val instance = publicMethod.getInstance().execute()
        instance.uri shouldBeEqualTo "test.com"
        instance.title shouldBeEqualTo "test.com"
        instance.description shouldBeEqualTo "description"
        instance.email shouldBeEqualTo "owner@test.com"
        instance.version shouldBeEqualTo "1.3.2"
    }

    @Test 
    fun getInstanceWithJson() {
        val client = MockClient.mock("instance.json")
        val publicMethod = Public(client)

        // this should work.
        /*

         publicMethod.getInstance()
            .doOnJson {
                it shouldBeEqualTo """{
  "uri": "test.com",
  "title": "test.com",
  "description": "description",
  "email": "owner@test.com",
  "version": "1.3.2"
}
                    """
            }
            .execute()

         */
    }

    @Test 
    fun getInstanceWithException() {
        assertFailsWith<Mastodon4jRequestException>{
            val client = MockClient.ioException()
            val publicMethod = Public(client)
            publicMethod.getInstance().execute()
        }
    }

    @Test 
    fun getSearch() {
        val client = MockClient.mock("search.json")

        val publicMethod = Public(client)
        val result = publicMethod.getSearch("test").execute()
        result.statuses.size shouldBeEqualTo 0
        result.accounts.size shouldBeEqualTo 6
        result.hashtags.size shouldBeEqualTo 5
        result.hashtags.size shouldBeEqualTo 5
    }

    @Test 
    fun getSearchWithException() {
        assertFailsWith<Mastodon4jRequestException>{
            val client = MockClient.ioException()
            val publicMethod = Public(client)
            publicMethod.getSearch("test").execute()
        }
    }

    @Test 
    fun getLocalPublic() {
        val client = MockClient.mock("public_timeline.json", maxId = 3L, sinceId = 1L)
        val publicMethod = Public(client)
        val statuses = publicMethod.getLocalPublic().execute()
        statuses.part.size shouldBeEqualTo 20
        statuses.link?.let {
            it.nextPath shouldBeEqualTo "<https://mstdn.jp/api/v1/timelines/public?limit=20&local=true&max_id=3>"
            it.maxId shouldBeEqualTo 3L
            it.prevPath shouldBeEqualTo "<https://mstdn.jp/api/v1/timelines/public?limit=20&local=true&since_id=1>"
            it.sinceId shouldBeEqualTo 1L
        }
    }

    @Test 
    fun getLocalPublicWithJson() {
        val atomicInt = AtomicInteger(0)
        val client = MockClient.mock("public_timeline.json", maxId = 3L, sinceId = 1L)
        val publicMethod = Public(client)
        publicMethod.getLocalPublic()
            .doOnJson {
                atomicInt.incrementAndGet()
            }
            .execute()
        atomicInt.get() shouldBeEqualTo 20
    }

    @Test 
    fun getLocalPublicWithException() {
        assertFailsWith<Mastodon4jRequestException>{
            val client = MockClient.ioException()
            val publicMethod = Public(client)
            publicMethod.getLocalPublic().execute()
        }
    }

    @Test 
    fun getLocalTag() {
        val client = MockClient.mock("tag.json", maxId = 3L, sinceId = 1L)
        val publicMethod = Public(client)
        val statuses = publicMethod.getLocalTag("mastodon").execute()
        statuses.part.size shouldBeEqualTo 20
    }

    @Test 
    fun getLocalTagWithException() {
        assertFailsWith<Mastodon4jRequestException>{
            val client = MockClient.ioException()
            val publicMethod = Public(client)
            publicMethod.getLocalTag("mastodon").execute()
        }
    }

}
