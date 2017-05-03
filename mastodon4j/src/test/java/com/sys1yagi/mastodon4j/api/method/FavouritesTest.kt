package com.sys1yagi.mastodon4j.api.method

import com.sys1yagi.mastodon4j.api.exception.Mastodon4jRequestException
import com.sys1yagi.mastodon4j.testtool.MockClient
import org.amshove.kluent.shouldEqualTo
import org.junit.Test

import org.junit.Assert.*
import java.net.SocketTimeoutException

class FavouritesTest {
    @Test
    fun getFavourites() {
        val client = MockClient.mock("favourites.json")

        val favorites = Favourites(client)
        val pageable = favorites.getFavourites().execute()
        val status = pageable.part.first()
        status.id shouldEqualTo 1111
    }

    @Test(expected = Mastodon4jRequestException::class)
    fun exception() {
        val client = MockClient.ioException()

        val favorites = Favourites(client)
        favorites.getFavourites().execute()
    }
}
