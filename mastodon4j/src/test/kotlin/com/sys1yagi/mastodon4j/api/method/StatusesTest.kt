package com.sys1yagi.mastodon4j.api.method

import com.sys1yagi.mastodon4j.api.exception.Mastodon4jRequestException
import com.sys1yagi.mastodon4j.testtool.MockClient
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldNotBe
import kotlin.test.Test
import kotlin.test.Ignore
import kotlin.test.assertFailsWith

import org.junit.Assert.*

class StatusesTest {
    @Test 
    fun getStatus() {
        val client = MockClient.mock("status.json")
        val statuses = Statuses(client)
        val status = statuses.getStatus(1L).execute()
        status.id shouldBeEqualTo 11111L
    }

    @Test 
    fun getStatusWithException() {
        assertFailsWith<Mastodon4jRequestException>{
            val client = MockClient.ioException()
            val statuses = Statuses(client)
            statuses.getStatus(1L).execute()
        }
    }

    @Test 
    fun getContext() {
        val client = MockClient.mock("context.json")
        val statuses = Statuses(client)
        val context = statuses.getContext(1L).execute()
        context.ancestors.size shouldBeEqualTo 2
        context.descendants.size shouldBeEqualTo 1
    }

    @Test 
    fun getContextWithException() {
        assertFailsWith<Mastodon4jRequestException>{
            val client = MockClient.ioException()
            val statuses = Statuses(client)
            statuses.getContext(1L).execute()
        }
    }

    @Test 
    fun getCard() {
        val client = MockClient.mock("card.json")
        val statuses = Statuses(client)
        val card = statuses.getCard(1L).execute()
        card.url shouldBeEqualTo "The url associated with the card"
        card.title shouldBeEqualTo "The title of the card"
        card.description shouldBeEqualTo "The card description"
        card.image shouldNotBe null
    }

    @Test 
    fun getCardWithException() {
        assertFailsWith<Mastodon4jRequestException>{
            val client = MockClient.ioException()
            val statuses = Statuses(client)
            statuses.getCard(1L).execute()
        }
    }

    @Test 
    fun getRebloggedBy() {
        val client = MockClient.mock("reblogged_by.json")
        val statuses = Statuses(client)
        val pageable = statuses.getRebloggedBy(1L).execute()
        val account = pageable.part.first()
        account.acct shouldBeEqualTo "test@test.com"
        account.displayName shouldBeEqualTo "test"
        account.userName shouldBeEqualTo "test"
    }

    @Test 
    fun getRebloggedByWithException() {
        assertFailsWith<Mastodon4jRequestException>{
            val client = MockClient.ioException()
            val statuses = Statuses(client)
            statuses.getRebloggedBy(1L).execute()
        }
    }

    @Test 
    fun getFavouritedBy() {
        val client = MockClient.mock("reblogged_by.json")
        val statuses = Statuses(client)
        val pageable = statuses.getFavouritedBy(1L).execute()
        val account = pageable.part.first()
        account.acct shouldBeEqualTo "test@test.com"
        account.displayName shouldBeEqualTo "test"
        account.userName shouldBeEqualTo "test"
    }

    @Test 
    fun getFavouritedByWithException() {
        assertFailsWith<Mastodon4jRequestException>{
            val client = MockClient.ioException()
            val statuses = Statuses(client)
            statuses.getFavouritedBy(1L).execute()
        }
    }

    @Test 
    fun postStatus() {
        val client = MockClient.mock("status.json")
        val statuses = Statuses(client)
        val status = statuses.postStatus("a", null, null, false, null).execute()
        status.id shouldBeEqualTo 11111L
    }

    @Test 
    fun postStatusWithException() {
        assertFailsWith<Mastodon4jRequestException>{
            val client = MockClient.ioException()
            val statuses = Statuses(client)
            statuses.postStatus("a", null, null, false, null).execute()
        }
    }

    @Test 
    fun postReblog() {
        val client = MockClient.mock("status.json")
        val statuses = Statuses(client)
        val status = statuses.postReblog(1L).execute()
        status.id shouldBeEqualTo 11111L
    }

    @Test 
    fun postReblogWithException() {
        assertFailsWith<Mastodon4jRequestException>{
            val client = MockClient.ioException()
            val statuses = Statuses(client)
            statuses.postReblog(1L).execute()
        }
    }

    @Test 
    fun postUnreblog() {
        val client = MockClient.mock("status.json")
        val statuses = Statuses(client)
        val status = statuses.postUnreblog(1L).execute()
        status.id shouldBeEqualTo 11111L
    }

    @Test 
    fun postUnreblogWithException() {
        assertFailsWith<Mastodon4jRequestException>{
            val client = MockClient.ioException()
            val statuses = Statuses(client)
            statuses.postUnreblog(1L).execute()
        }
    }

    @Test 
    fun postFavourite() {
        val client = MockClient.mock("status.json")
        val statuses = Statuses(client)
        val status = statuses.postFavourite(1L).execute()
        status.id shouldBeEqualTo 11111L
    }

    @Test 
    fun postFavouriteWithException() {
        assertFailsWith<Mastodon4jRequestException>{
            val client = MockClient.ioException()
            val statuses = Statuses(client)
            statuses.postFavourite(1L).execute()
        }
    }

    @Test 
    fun postUnfavourite() {
        val client = MockClient.mock("status.json")
        val statuses = Statuses(client)
        val status = statuses.postUnfavourite(1L).execute()
        status.id shouldBeEqualTo 11111L
    }

    @Test 
    fun postUnfavouriteWithException() {
        assertFailsWith<Mastodon4jRequestException>{
            val client = MockClient.ioException()
            val statuses = Statuses(client)
            statuses.postUnfavourite(1L).execute()
        }
    }
}
