package com.sys1yagi.mastodon4j.api.method

import com.sys1yagi.mastodon4j.api.exception.Mastodon4jRequestException
import com.sys1yagi.mastodon4j.testtool.MockClient
import org.amshove.kluent.shouldEqualTo
import org.amshove.kluent.shouldNotBe
import org.junit.Test

import org.junit.Assert.*

class StatusesTest {
    @Test
    fun getStatus() {
        val client = MockClient.mock("status.json")
        val statuses = Statuses(client)
        val status = statuses.getStatus(1L).execute()
        status.id shouldEqualTo 11111L
    }

    @Test(expected = Mastodon4jRequestException::class)
    fun getStatusWithException() {
        val client = MockClient.ioException()
        val statuses = Statuses(client)
        statuses.getStatus(1L).execute()
    }

    @Test
    fun getContext() {
        val client = MockClient.mock("context.json")
        val statuses = Statuses(client)
        val context = statuses.getContext(1L).execute()
        context.ancestors.size shouldEqualTo 2
        context.descendants.size shouldEqualTo 1
    }

    @Test(expected = Mastodon4jRequestException::class)
    fun getContextWithException() {
        val client = MockClient.ioException()
        val statuses = Statuses(client)
        statuses.getContext(1L).execute()
    }

    @Test
    fun getCard() {
        val client = MockClient.mock("card.json")
        val statuses = Statuses(client)
        val card = statuses.getCard(1L).execute()
        card.url shouldEqualTo "The url associated with the card"
        card.title shouldEqualTo "The title of the card"
        card.description shouldEqualTo "The card description"
        card.image shouldNotBe null
    }

    @Test(expected = Mastodon4jRequestException::class)
    fun getCardWithExcetpion() {
        val client = MockClient.ioException()
        val statuses = Statuses(client)
        statuses.getCard(1L).execute()
    }

    @Test
    fun getRebloggedBy() {
        val client = MockClient.mock("reblogged_by.json")
        val statuses = Statuses(client)
        val pageable = statuses.getRebloggedBy(1L).execute()
        val account = pageable.part.first()
        account.acct shouldEqualTo "test@test.com"
        account.displayName shouldEqualTo "test"
        account.userName shouldEqualTo "test"
    }

    @Test(expected = Mastodon4jRequestException::class)
    fun getRebloggedByWithException() {
        val client = MockClient.ioException()
        val statuses = Statuses(client)
        statuses.getRebloggedBy(1L).execute()
    }

    @Test
    fun getFavouritedBy() {
        val client = MockClient.mock("reblogged_by.json")
        val statuses = Statuses(client)
        val pageable = statuses.getFavouritedBy(1L).execute()
        val account = pageable.part.first()
        account.acct shouldEqualTo "test@test.com"
        account.displayName shouldEqualTo "test"
        account.userName shouldEqualTo "test"
    }

    @Test(expected = Mastodon4jRequestException::class)
    fun getFavouritedByWithException() {
        val client = MockClient.ioException()
        val statuses = Statuses(client)
        statuses.getFavouritedBy(1L).execute()
    }

    @Test
    fun postStatus() {
        val client = MockClient.mock("status.json")
        val statuses = Statuses(client)
        val status = statuses.postStatus("a", null, null, null, false, null).execute()
        status.id shouldEqualTo 11111L
    }

    @Test(expected = Mastodon4jRequestException::class)
    fun postStatusWithException() {
        val client = MockClient.ioException()
        val statuses = Statuses(client)
        statuses.postStatus("a", null, null, null, false, null).execute()
    }

    @Test
    fun postReblog() {
        val client = MockClient.mock("status.json")
        val statuses = Statuses(client)
        val status = statuses.postReblog(1L).execute()
        status.id shouldEqualTo 11111L
    }

    @Test(expected = Mastodon4jRequestException::class)
    fun postReblogWithException() {
        val client = MockClient.ioException()
        val statuses = Statuses(client)
        statuses.postReblog(1L).execute()
    }

    @Test
    fun postUnreblog() {
        val client = MockClient.mock("status.json")
        val statuses = Statuses(client)
        val status = statuses.postUnreblog(1L).execute()
        status.id shouldEqualTo 11111L
    }

    @Test(expected = Mastodon4jRequestException::class)
    fun postUnreblogWithException() {
        val client = MockClient.ioException()
        val statuses = Statuses(client)
        statuses.postUnreblog(1L).execute()
    }

    @Test
    fun postFavourite() {
        val client = MockClient.mock("status.json")
        val statuses = Statuses(client)
        val status = statuses.postFavourite(1L).execute()
        status.id shouldEqualTo 11111L
    }

    @Test(expected = Mastodon4jRequestException::class)
    fun postFavouriteWithException() {
        val client = MockClient.ioException()
        val statuses = Statuses(client)
        statuses.postFavourite(1L).execute()
    }

    @Test
    fun postUnfavourite() {
        val client = MockClient.mock("status.json")
        val statuses = Statuses(client)
        val status = statuses.postUnfavourite(1L).execute()
        status.id shouldEqualTo 11111L
    }

    @Test(expected = Mastodon4jRequestException::class)
    fun postUnfavouriteWithException() {
        val client = MockClient.ioException()
        val statuses = Statuses(client)
        statuses.postUnfavourite(1L).execute()
    }
}
