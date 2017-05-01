package com.sys1yagi.mastodon4j.api.method

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
        val pageable = favorites.getFavourites()
        val status = pageable.part.first()
        status.id shouldEqualTo 1111
    }

    @Test(expected = SocketTimeoutException::class)
    fun exception() {
        val client = MockClient.ioException()

        val favorites = Favourites(client)
        favorites.getFavourites()
    }
}
